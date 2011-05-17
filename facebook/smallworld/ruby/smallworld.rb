#!/usr/bin/ruby

# Region quad tree
class RegionTreeNode
  attr_accessor :left, :right, :upper, :lower
  attr_accessor :id, :lat, :long
  attr_accessor :parent, :children
  attr_accessor :point_count

  def initialize
    @point_count = 0
    @children = nil
    @lat = nil
    @long = nil
    @id = -1
    #puts "creating RegionTreeNode point_count=#{@point_count} children=#{@children} parent=#{@parent}"
  end

  def to_string
    return "left=#{@left} right=#{@right} upper=#{@upper} lower=#{@lower} point_count=#{@point_count} lat=#{@lat} long=#{@long}"
  end

  def print_levels
    fifo_q = [self]
    current_level_count = 1
    while (!fifo_q.empty?)
      puts "current level count = #{current_level_count}"
      current = fifo_q.shift
      puts current.to_string
      if (current.children)
        current.children.each { |x| fifo_q.push(x) }
      end
      current_level_count -= 1
      if (current_level_count == 0)
        print "\n"
        current_level_count = fifo_q.size
      end
    end
    print "\n"
  end

  # insert a node into the tree
  # rooted at tree_root
  def insert(i_id, i_lat, i_long)
    # look for a leaf node
    # whose region does not contain any point
    current_node = self
    prev_node = current_node.parent
    while (current_node.children && current_node.point_count>0)
      # pick a child with the correct region
      current_node.point_count += 1
      #puts "#{i_lat}, #{i_long}"
      #print "current: "
      #puts current_node.to_string
      found = false
      for child in current_node.children
        #print "child: "
        #puts child.to_string
        # what about point on the boundary
        if (child && child.left<=i_long && child.right>i_long && child.lower<=i_lat && child.upper>i_lat)
          #print "find child "
          #puts child.to_string
          prev_node = current_node.parent
          current_node = child
          found = true
          break
        end
      end
      if (!found)
        puts "ERROR (#{i_id}, #{i_lat}, #{i_long}) out of range"
        exit
      end
    end # end of while

    if (current_node.point_count == 0)
      # empty region
      #puts "empty region #{self}"
      #puts current_node.to_string
      current_node.lat = i_lat
      current_node.long = i_long
      current_node.id = i_id
      current_node.point_count += 1
    else
      #puts "split region #{self}"
      # need to split the node
      current_node.children = []
      # upper left
      new_node1 = RegionTreeNode.new
      new_node1.left = current_node.left
      new_node1.right = (current_node.left+current_node.right)/2
      new_node1.upper = current_node.upper
      new_node1.lower = (current_node.upper+current_node.lower)/2
      new_node1.parent = current_node
      current_node.children.push(new_node1)
      # lower left
      new_node2 = RegionTreeNode.new
      new_node2.left = current_node.left
      new_node2.right = (current_node.left+current_node.right)/2
      new_node2.upper = (current_node.upper+current_node.lower)/2
      new_node2.lower = current_node.lower
      current_node.children.push(new_node2)
      new_node2.parent = current_node
      # lower right
      new_node3 = RegionTreeNode.new
      new_node3.left = (current_node.left+current_node.right)/2
      new_node3.right = current_node.right
      new_node3.upper = (current_node.upper+current_node.lower)/2
      new_node3.lower = current_node.lower
      current_node.children.push(new_node3)
      new_node3.parent = current_node
      # upper right
      new_node4 = RegionTreeNode.new
      new_node4.left = (current_node.left+current_node.right)/2
      new_node4.right = current_node.right
      new_node4.upper = current_node.upper
      new_node4.lower = (current_node.upper+current_node.lower)/2
      current_node.children.push(new_node4)
      new_node4.parent = current_node
      # reassign points
      for child in current_node.children
        if (child && child.left<=i_long && child.right>i_long && child.lower<=i_lat && child.upper>i_lat)
          # add to parent's point count
          current_node.point_count += 1
          child.insert(i_id, i_lat, i_long)
        end
        if (child && child.left<=current_node.long && child.right>current_node.long && child.lower<=current_node.lat && child.upper>current_node.lat)
          child.insert(current_node.id, current_node.lat, current_node.long)
        end
      end
      current_node.lat = nil
      current_node.long = nil
      #puts current_node.to_string
      #current_node.children.each { |x| puts x.to_string } unless !current_node.children
    end
  end

  # find closest friends
  # valid only for a leaf node
  def closest_friends(max_heap, tree_root)
    # if this tree root is leaf
    if (!tree_root.children)
      return unless tree_root.point_count==1
      new_node = MaxHeapNode.new
      new_node.id = tree_root.id
      new_node.lat = tree_root.lat
      new_node.long = tree_root.long
      new_node.distance = cartesian_distance(tree_root, self)
      insert_max_heap(max_heap, new_node)
      #max_heap.each {|x| print "#{x.id},#{x.lat},#{x.long},#{x.distance}\t"}
      #print "\n"
    else
      for child in tree_root.children
        next unless child!=self
        next unless max_heap.size<3 or point_region_distance(child, self)<max_heap[0].distance
        closest_friends(max_heap, child)
      end
    end
  end

  class MaxHeapNode
    attr_accessor :id, :lat, :long, :distance
  end

  def insert_max_heap(max_heap, node)
    if (max_heap.size<3)
      max_heap.push(node)
      if (max_heap[-1].distance>max_heap[0].distance)
        tmp = max_heap[-1]
        max_heap[-1] = max_heap[0]
        max_heap[0] = tmp
      end
    else
      if (node.distance<max_heap[0].distance)
        max_heap[0] = node
        max_id = -1
        if (max_heap[1].distance<=max_heap[2].distance)
          max_id = 2
        else 
          max_id = 1
        end
        if (max_heap[0].distance<max_heap[max_id].distance)
          tmp = max_heap[0]
          max_heap[0] = max_heap[max_id]
          max_heap[max_id] = tmp
        end
      end
    end
  end

  # calculate the distance between two friends
  def cartesian_distance(friend, me)
    return (friend.lat-me.lat)**2 + (friend.long-me.long)**2
  end

  # return the closest distance between a point
  # and a retangular region
  def point_region_distance(region, me)
    distance1 = -1
    distance2 = -1
    if (me.long>=region.right) 
      distance1 = (region.right-me.long)**2 + (region.upper-me.lat)**2
      distance2 = (region.right-me.long)**2 + (region.lower-me.lat)**2
    elsif (me.long<=region.left)
      distance1 = (region.left-me.long)**2 + (region.upper-me.lat)**2
      distance2 = (region.left-me.long)**2 + (region.lower-me.lat)**2
    elsif (me.lat>=region.upper)
      distance1 = (region.left-me.long)**2 + (region.upper-me.lat)**2
      distance2 = (region.right-me.long)**2 + (region.upper-me.lat)**2
    elsif (me.lat<=region.lower)
      distance1 = (region.left-me.long)**2 + (region.lower-me.lat)**2
      distance2 = (region.right-me.long)**2 + (region.lower-me.lat)**2
    end

    if (distance1<distance2)
      return distance1
    else
      return distance2
    end
  end

end

$results = {}
# DFS tree
def traverse_tree(tree_root)
  if (!tree_root.children)
    if (tree_root.point_count==1)
      max_heap = []
      #puts "working on #{tree_root.id} ..."
      tree_root.closest_friends(max_heap, $tree_root)
      sorted_result = [max_heap[0].id]
      if (max_heap[1].distance < max_heap[2].distance)
        sorted_result.unshift(max_heap[2].id)
        sorted_result.unshift(max_heap[1].id)
      else
        sorted_result.unshift(max_heap[1].id)
        sorted_result.unshift(max_heap[2].id)
      end
      $results[tree_root.id] = sorted_result
    end
  else
    tree_root.children.each { |x| traverse_tree(x) }
  end
end

# create the tree root
$tree_root = RegionTreeNode.new
$tree_root.left = -3600.0
$tree_root.right = 3600.0
$tree_root.upper = 1800.0
$tree_root.lower = -1800.0
$tree_root.parent = nil

# process input file
# and build a region tree from the points
$num_people = 0
begin
  file = File.new(ARGV[0], "r")
  while (line = file.gets)
    tmp = line.split(/\s+/)
    #puts "#{tmp[0]}, #{tmp[1]}, #{tmp[2]}"
    $tree_root.insert(tmp[0].to_i, tmp[1].to_f, tmp[2].to_f)
    $num_people += 1
  end
  file.close
rescue => err
  puts "Exception: #{err}:"
  err
end

#$tree_root.print_levels
traverse_tree($tree_root)

for i in 1..$num_people
  puts "#{i} #{$results[i][0]},#{$results[i][1]},#{$results[i][2]}"
end
