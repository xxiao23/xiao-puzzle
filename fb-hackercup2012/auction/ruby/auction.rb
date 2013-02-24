#!/usr/bin/env ruby

#########################
#         classes       #
#########################
class RangeTreeNode
  attr_accessor :value, :left_child, :right_child, :parent, :inner_tree_root, :size
  
  def initialize(value)
    @value = value
    @left_child = nil
    @right_child = nil
    @parent = nil
    @inner_tree_root = nil
    @size = 1
  end
  
  def to_s
    "#{@value}/#{@size}"
  end
end

#########################
#      sub routines     #
#########################
# build a 2D range tree using all items
def build_2d_range_tree item_array
  root = nil
  item_array.each { |item|
    root = insert_into_2d_range_tree root, item, 1
  }
  return root
end

# recursivly insert into 2 levels of 
# 1D range trees
def insert_into_2d_range_tree root, item, level
  # only 2 levels
  return nil if level > 2
  
  # real work
  insert_place = root
  insert_parent = nil
  while !insert_place.nil?
    insert_parent = insert_place
    # update secondary range tree 
    insert_place.inner_tree_root = insert_into_2d_range_tree insert_place.inner_tree_root, item, level+1
    insert_place.size += 1
    # primary range tree is based on price
    if item[level-1] <= insert_place.value then
      insert_place = insert_place.left_child
    else
      insert_place = insert_place.right_child
    end
  end
  if insert_parent.nil? then
    # root is nil
    root = RangeTreeNode.new(item[level-1])
    root.inner_tree_root = insert_into_2d_range_tree root.inner_tree_root, item, level+1
  else
    if insert_parent.value != item[level-1] then
      new_node = RangeTreeNode.new(item[level-1])
      new_node.parent = insert_parent
      new_node.inner_tree_root = RangeTreeNode.new(item[level])
      clone_node = RangeTreeNode.new(insert_parent.value)
      clone_node.size = insert_parent.size - 1
      clone_node.parent = insert_parent
      clone_w_value = 0
      if level <= 1 and !insert_parent.inner_tree_root.left_child.nil? and insert_parent.inner_tree_root.left_child.value != item[level]
        clone_w_value = insert_parent.inner_tree_root.left_child.value
      elsif level <= 1 and !insert_parent.inner_tree_root.right_child.nil? and insert_parent.inner_tree_root.right_child.value != item[level]
        clone_w_value = insert_parent.inner_tree_root.right_child.value
      else
        clone_w_value = item[level]
      end
      clone_node.inner_tree_root = RangeTreeNode.new(clone_w_value) if level <= 1
      if item[level-1] <= insert_parent.value then
        insert_parent.left_child = new_node
        insert_parent.right_child = clone_node
        insert_parent.value = new_node.value
      else
        insert_parent.right_child = new_node
        insert_parent.left_child = clone_node
      end
    end
  end
  return root
end

def print_1d_range_tree_by_layer root, level
  return if root.nil?
  
  queue = Array.new
  queue.push root
  queue.push "\n"
  while !queue.empty?
    first = queue.delete_at 0
    if first != "\n" then
      print "level=#{level} #{first}:#{first.to_s} "
      if level <= 1 then
        puts "\nlevel=#{level} inner tree:"
        print_1d_range_tree_by_layer first.inner_tree_root, level+1
      end
      queue.push first.left_child if !first.left_child.nil?
      queue.push first.right_child if !first.right_child.nil?
    elsif first == "\n" and !queue.empty? then
      print "\n"
      queue.push "\n"
    end
  end
  print "\n"
end

# 1D range query
def range_query_1d root, lower_point, upper_point, level
  return 0 if level > 2
  num_items_in_rect = 0
  upper = upper_point[level-1]
  lower = lower_point[level-1]
  w = root
  while (!w.left_child.nil? or !w.right_child.nil?) and (upper <= w.value or lower > w.value)
    if upper <= w.value
      w = w.left_child
    else
      w = w.right_child
    end
  end
  # after while loop
  # w is the split node
  if w.left_child.nil? and w.right_child.nil? then
    if level < 2 then
      num_items_in_rect += range_query_1d w.inner_tree_root, lower_point, upper_point, level+1 
    else
      num_items_in_rect += 1 if lower <= w.value and upper >= w.value
    end
  else
    # first, do left traversal
    v = w.left_child
    while !v.nil? and (!v.left_child.nil? or !v.right_child.nil?)
      if lower <= v.value then
        if !v.right_child.nil? then
          num_items_in_rect += range_query_1d v.right_child.inner_tree_root, lower_point, upper_point, level+1 
        end
        v = v.left_child
      else
        v = v.right_child
      end
    end
    if !v.nil?
      if level < 2
        num_items_in_rect += range_query_1d v.inner_tree_root, lower_point, upper_point, level+1 
      else
        num_items_in_rect += v.size if lower <= v.value and upper >= v.value
      end
    end
    # second, do right traversal
    v = w.right_child
    while !v.nil? and (!v.left_child.nil? or !v.right_child.nil?)
      if upper > v.value then
        if !v.left_child.nil?
          num_items_in_rect += range_query_1d v.left_child.inner_tree_root, lower_point, upper_point, level+1 
        end
        v = v.right_child
      else
        v = v.left_child
      end
    end
    if !v.nil? then
      if level < 2
        num_items_in_rect += range_query_1d v.inner_tree_root, lower_point, upper_point, level+1
      else
        num_items_in_rect += 1 if lower <= v.value and upper >= v.value
      end
    end
  end
  
  return num_items_in_rect;
end

def get_auction_stats param_array
  n_s, p1_s, w1_s, m_s, k_s, a_s, b_s, c_s, d_s = param_array
  num_bargains = 0
  num_terribles = 0
  n = n_s.to_i
  p1 = p1_s.to_i
  w1 = w1_s.to_i
  m = m_s.to_i
  $M = m_s.to_i
  k = k_s.to_i
  $K = k_s.to_i
  a = a_s.to_i
  b = b_s.to_i
  c = c_s.to_i
  d = d_s.to_i
  item_array = Array.new # each element is [price, weight]
  pi = p1.to_i
  wi = w1.to_i
  item_array.push [p1, w1] # add the first item
  # construct the item array 
  for i in (2..n)
    pi = ((a * pi + b) % m) + 1
    wi = ((c * wi + d) % k) + 1
    item_array.push [pi, wi]
  end 
  # build a 2D range tree for each item
  tree_root = build_2d_range_tree item_array
  item_array.each { |item|
    num_better_items = range_query_1d tree_root, [0,0], item, 1
    num_bargains +=1 if num_better_items == 1 # item itself always count
    num_worse_items = range_query_1d tree_root, item, [$M, $K], 1
    num_terribles += 1 if num_worse_items == 1 # item itself always count
  }
  return num_terribles, num_bargains
end

#########################
#    process arguments  #
#########################
if ARGV[0].nil?
  raise "missing input file"
end

input_file_name = ARGV[0]

#########################
#    global variables   #
#########################
$M = 0
$K = 0

#########################
#   process input file  #
#########################
input_file = File.new(input_file_name, 'r')
is_first_line = true
case_num = 1
while line = input_file.gets
  if is_first_line then
    is_first_line = false
    $num_test_cases = line.chomp().to_i
    next
  end
  tmp = line.chomp().split(/\s+/)
  auction_stats = get_auction_stats tmp
  puts "Case ##{case_num}: #{auction_stats[0]} #{auction_stats[1]}"
  case_num += 1
end