#!/usr/bin/env ruby

puts "merge two binary search trees!"

######################################
#          class definitions         #
######################################

# a binary tree node
# value is comparable
# left is its left child
# right is its right child
class BstNode
  
  attr_accessor :value, :left, :right
  
  def initialize(value, left, right)
    @value = value
    @left = left
    @right = right
  end
  
end
#########################
#    global variables   #
#########################
$bstRoots = Array.new

######################################
#             sub routines           #
######################################

# print BST in order
def print_BST_in_order(root)
  if root.nil?
    return
  end
  print_BST_in_order(root.left)
  print "#{root.value} "
  print_BST_in_order(root.right)
end

# print BST by layer
def print_BST_by_layer(root)
  puts "print BST rooted at #{root.value} by layer" if $debug
  print_queue = Array.new
  level_queue = Array.new
  print_queue.push root
  level_queue.push 1
  prev_level = 1
  while !print_queue.empty?
    bst_node = print_queue.first
    print_queue.delete_at 0
    cur_level = level_queue.first
    level_queue.delete_at 0
    print "\n" if cur_level != prev_level
    print "#{bst_node.value} "
    prev_level = cur_level
    if !bst_node.left.nil?
      print_queue.push bst_node.left
      level_queue.push cur_level+1
    end
    if !bst_node.right.nil?
      print_queue.push bst_node.right
      level_queue.push cur_level+1
    end
  end
end

# print a singly linked list
# using left child pointer
def print_linked_list_left(head)
  while !head.nil?
    print "#{head.value} "
    head = head.left
  end
end


# print a singly linked list
# using right child pointer
def print_linked_list_right(head)
  while !head.nil?
    print "#{head.value} "
    head = head.right
  end
end

# convert a BST to a singly linked list,
# using the left child pointer of every BST node
# and append to a existing list head
#
# return the head of resulted linked list
def convert_bst_to_singly_linked_list(root, head)
  # if tree is empty, do nothing
  if root.nil?
    return head
  end
  # convert left sub-tree
  head = convert_bst_to_singly_linked_list root.left, head
  # append the root itself
  root.left = head
  puts "appending #{root.value} to " + (head.nil? ? "nil" : "#{head.value}") if $debug
  # convert right sub-tree
  head = convert_bst_to_singly_linked_list root.right, root
  # return the new head
  return head
end

# insert new node into the merged listed list
# and shift bst_node one node left
def insert_into_merged_list_and_shift_left(bst_node, head)
  puts "appending #{bst_node.value} to " + (head.nil? ? "nil" : "#{head.value}") if $debug
  bst_node.right = head
  head = bst_node
  bst_node = bst_node.left
end

# merge two sorted linked list
#
# return the head of the merged linked list
def merge_two_sorted_linked_lists(head_1, head_2)
  puts "merged two linked list #{head_1.value} and #{head_2.value}" if $debug
  # merged_head points to the current head of the merged linked list
  # using BST node's right child pointer
  merged_head = nil
  merged_list_length = 0
  # while either lists to be merged is not exhausted
  while (!head_1.nil? || !head_2.nil?)
    # insert the rest of list 2 into the merged list
    if head_1.nil? 
      while !head_2.nil?
        #insert_into_merged_list_and_shift_left head_2, merged_head
        puts "appending #{head_2.value} to " + (merged_head.nil? ? "nil" : "#{merged_head.value}") if $debug
        head_2.right = merged_head
        merged_head = head_2
        head_2 = head_2.left
        merged_list_length += 1
      end
      next # head_1 and head_2 both are nil now
    end
    # insert the rest of list 1 into the merged list
    if head_2.nil? 
      while !head_1.nil?
        #insert_into_merged_list_and_shift_left head_1, merged_head
        puts "appending #{head_1.value} to " + (merged_head.nil? ? "nil" : "#{merged_head.value}") if $debug
        head_1.right = merged_head
        merged_head = head_1
        head_1 = head_1.left
        merged_list_length += 1
      end
      next # head_1 and head_2 both are nil now
    end
    # compare head_1 and head_2
    # insert the large one into the merged list
    if head_1.value.to_i > head_2.value.to_i
      # insert head_1
      #insert_into_merged_list_and_shift_left head_1, merged_head
      puts "appending #{head_1.value} to " + (merged_head.nil? ? "nil" : "#{merged_head.value}") if $debug
      head_1.right = merged_head
      merged_head = head_1
      head_1 = head_1.left
      merged_list_length += 1
    else
      # insert head_2
      #insert_into_merged_list_and_shift_left head_2, merged_head
      puts "appending #{head_2.value} to " + (merged_head.nil? ? "nil" : "#{merged_head.value}") if $debug
      head_2.right = merged_head
      merged_head = head_2
      head_2 = head_2.left
      merged_list_length += 1
    end
  end
  # return the head of the merged linked list
  return merged_head, merged_list_length
end

# convert a linked list of certain length into a balanced BST
#
# return the root of the BST
def convert_linked_list_to_balanced_BST(head, length)
  
  # trivial case
  # return immediately
  if head.nil? || length == 0
    return nil
  end

  puts "working on head = #{head.value}" if $debug
  
  left_half_size = (length - 1) / 2
  right_half_size = length - 1 - left_half_size
  puts "left_half_size = #{left_half_size} right_half_size = #{right_half_size}" if $debug
  
  root = head
  i = 0
  while i < left_half_size
    puts "shift to #{root.value}" if $debug
    root = root.right
    i += 1
  end
  
  # now leaf_tail points to the end of left sub-list
  puts "splitting on root = #{root.value}" if $debug
  
  # convert the left sub-list
  left_child = convert_linked_list_to_balanced_BST(head, left_half_size)
  # convert the right sub-list
  right_child = convert_linked_list_to_balanced_BST(root.right, right_half_size)
  # connect children to their paret
  root.left = left_child
  root.right = right_child
  puts "build up sub-tree rooted on #{root.value}" if $debug
  # return the root of BST
  return root
end

######################################
#       process input file           #
######################################

if (ARGV[1] == "debug")
  $debug = true
else
  $debug = false
end

file = File.new(ARGV[0], "r")
value_tree_node_hash = Hash.new # key => BST node.value; value => BST node
while line = file.gets
  puts "#{line}" if $debug
  next if line =~ /^#/
  tmp = line.chomp.split(/\s+/)
  puts "#{tmp.inspect}" if $debug
  value_tree_node_hash[tmp[0]] = BstNode.new(tmp[0], nil, nil)
  if (tmp[1] == "nil") 
    $bstRoots.push value_tree_node_hash[tmp[0]]
  else
    # this is not a root
    parent = value_tree_node_hash[tmp[1]];
    if parent.nil?
      raise RuntimeError, "Parent cannot be nil!"
    end
    # link to its parent
    case tmp[2]
    when "L"
      parent.left = value_tree_node_hash[tmp[0]]
    when "R"
      parent.right = value_tree_node_hash[tmp[0]]
    else
      raise RuntimeError, "Unrecognizable child tag #{tmp[2]}, should be either L or R"
    end
  end
end

if $debug
  $bstRoots.each { |r| print_BST_in_order r; puts "" }
end

######################################
#             main process           #
######################################

# convert two binary trees to sorted singly
# linked lists
bstLinkedLists = Array.new
$bstRoots.each { |r|
  head = nil;
  head = convert_bst_to_singly_linked_list r, head;
  puts "a new head #{head.value}" if $debug
  bstLinkedLists.push head
}

if $debug
  bstLinkedLists.each { |h| 
    print_linked_list_left h; puts "" 
  }
end

# merge two sorted singly linked linked lists
merged_head, merged_list_length = merge_two_sorted_linked_lists(bstLinkedLists[0], bstLinkedLists[1])
print_linked_list_right(merged_head) if $debug
puts "merged_list_length = #{merged_list_length}" if $debug

# convert a sorted singly linked list
# to a balanced binary search tree
merged_root = convert_linked_list_to_balanced_BST(merged_head, merged_list_length)
puts "merged balanced BST rooted at #{merged_root.value}" if $debug
print_BST_in_order(merged_root)
print "\n"
print_BST_by_layer(merged_root)
print "\n"