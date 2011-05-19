#!/usr/bin/ruby
# Point
class Point
  attr_accessor :id, :lat, :long

  def initialize(id, lat, long)
    @id = id
    @lat = lat
    @long = long
  end

end

# kd tree node
class Node
  attr_accessor :location, :left_child, :right_child
end

def kd_tree(point_list, depth=0)
  if !point_list
    return nil
  end
  #print "depth=#{depth} "
  #point_list.each {|x| print "#{x.id} "}
  #print "\n"

  # Select axis based on depth so that axis cycles through all valid values`
  axis = depth % 2
  
  # Sort point list and choose median as pivot element
  if (axis==0)
    sorted_list = point_list.sort_by {|a| a.lat}
  else
    sorted_list = point_list.sort_by {|a| a.long}
  end
  median = sorted_list.size/2
  #print "array="
  #sorted_list.each {|x| print "#{x.id} "}
  #print "median=#{median}"
  #print "\n"

  # Create node and construct subtrees
  node = Node.new
  node.location = sorted_list[median]
  if (median>0)
    node.left_child = kd_tree(sorted_list[0, median], depth+1)
  else
    node.left_child = nil
  end
  if (sorted_list.size>median+1)
    node.right_child = kd_tree(sorted_list[median+1, sorted_list.size-median-1], depth+1)
  else
    node.right_child = nil
  end
  return node
end

def print_kd_tree_by_level(tree_root)
  fifo_q = [tree_root]
  current_level_count = 1
  while (!fifo_q.empty?)
    current = fifo_q.shift
    print "#{current.location.id},#{current.location.lat},#{current.location.long} "
    # add all children to fifo queue
    if (current.left_child)
      fifo_q.push(current.left_child)
    end
    if (current.right_child)
      fifo_q.push(current.right_child)
    end
    # level ends here
    current_level_count -= 1
    if (current_level_count == 0)
      print "\n"
      # calculate new level count
      # what's currently in fifo
      current_level_count = fifo_q.size
      puts "current level count = #{current_level_count}"
    end
  end
  print "\n"
end

def distance(friend, me)
  return (friend.lat-me.lat)**2 + (friend.long-me.long)**2
end

# find the closest 3 friends
def kd_search_nn(here, point, best, depth=0)
  if (!here)
    return best
  end

  #puts "start node #{here.location.id} depth=#{depth}"

  if (here.location!=point and best.size<3)
    #print "insert #{here.location.id} into best="
    #best.each {|x| print "<#{x.location.id}> "}
    #print "\n"
    best.push(here)
    largest = 0
    if (best[1] and distance(best[1].location, point)>distance(best[0].location, point))
      largest = 1
    end
    if (best[2] and distance(best[2].location, point)>distance(best[largest].location, point))
      largest = 2
    end
    if (largest!=0)
      tmp = best[0]
      best[0] = best[largest]
      best[largest] = tmp
    end
  # consider the current node
  elsif (here.location!=point and distance(here.location, point)<distance(best[0].location, point))
    #print "try to fit #{here.location.id} into best="
    #best.each {|x| print "<#{x.location.id}> "}
    #print "\n"
    best[0] = here
    largest = 0
    if (distance(best[1].location, point)>distance(best[0].location, point))
      largest = 1
    end
    if (distance(best[2].location, point)>distance(best[largest].location, point))
      largest = 2
    end
    if (largest!=0)
      tmp = best[0]
      best[0] = best[largest]
      best[largest] = tmp
    end
  end

  # search the near brach
  if (depth%2==0)
    axis = here.location.lat
    pivot = point.lat
  else
    axis = here.location.long
    pivot = point.long
  end
  if pivot<=axis
    near_child = here.left_child
    far_child = here.right_child
  else
    near_child = here.right_child
    far_child = here.left_child
  end
  #print "search near child "
  best = kd_search_nn(near_child, point, best, depth+1)

  # search the away brnach maybe
  if ((axis-pivot).abs<distance(best[0].location,point))
    #print "search far child "
    best = kd_search_nn(far_child, point, best, depth+1)
  end

  #print "finish node #{here.location.id} depth=#{depth} best="
  #best.each {|x| print "<#{x.location.id}> "}
  #print "\n"
  return best
end

def print_closest_friends(point, best)
  print "#{point.id} "
  if distance(point, best[1].location)<distance(point, best[2].location)
    print "#{best[1].location.id},#{best[2].location.id},"
  else
    print "#{best[2].location.id},#{best[1].location.id},"
  end
  puts "#{best[0].location.id}"
end

# process input file
# and build a kd tree from the points
$num_people = 0
$points = []
begin
  file = File.new(ARGV[0], "r")
  while (line = file.gets)
    tmp = line.split(/\s+/)
    #puts "#{tmp[0]}, #{tmp[1]}, #{tmp[2]}"
    $points.push(Point.new(tmp[0].to_i, tmp[1].to_f, tmp[2].to_f))
    $num_people += 1
  end
  file.close
rescue => err
  puts "Exception: #{err}:"
  err
end

$tree_root = kd_tree($points)
#print_kd_tree_by_level($tree_root)
for point in $points
  best = []
  print_closest_friends(point, kd_search_nn($tree_root, point, best))
end
