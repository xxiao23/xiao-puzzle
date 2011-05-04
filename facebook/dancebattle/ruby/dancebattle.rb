#!/usr/bin/env ruby
#
$global_count = 0

class Player
  ME=0
  OPPONENT=1
  DEFAULT=2
end

class Prediction
  LOSE=0
  WIN=1
  DEFAULT=2
end

class PredictionTreeNode
  attr_accessor :player, :first_move, :second_move, :prediction, :p_parent, :p_children

  def initialize
    @player = Player::DEFAULT
    @first_move = -1
    @second_move = -2
    @prediction = Prediction::DEFAULT
    @p_parent = nil
    @p_children = [] 
    puts "create a new node"
  end
end

def print_moves
  for i in 0..$total_num_moves-1
    for j in 0..$total_num_moves-1
      print $move_combinations[i][j]
    end
    print "\n"
  end
end

# build a prediction tree
# the root node tells whether 
# this game can be won
def build_prediction_tree(tree_root, last_move)
  puts "tree_root #{tree_root}"
  preds = []
  # try each possible dance
  for i in 0..$total_num_moves-1
    if ($move_combinations[last_move][i] == 0)
      new_tree_node = PredictionTreeNode.new
      new_tree_node.p_parent = tree_root
      new_tree_node.player = assign_player(tree_root)
      new_tree_node.first_move = last_move
      new_tree_node.second_move = i
      tree_root.p_children.push(new_tree_node)
      $global_count += 1
      print "DEBUG #{$global_count} new tree node: "
      print_tree_node(new_tree_node)
      print "\n"
      # mark the combs as used
      $move_combinations[last_move][i] = 1
      $move_combinations[i][last_move] = 1
      # build a subtree
      prediction = build_prediction_tree(new_tree_node, i)
      preds.push(prediction)
      # undo the comb marks
      $move_combinations[last_move][i] = 0
      $move_combinations[i][last_move] = 0
    end
  end

  tree_root.prediction = combine_predictions(tree_root.player, preds)
  print "DEBUG #{tree_root} : #{preds}\t"
  print_tree_node(tree_root)
  print"\n"
  return tree_root.prediction
end

def assign_player(parent)
  puts "DEBUG parent player = #{parent.player}"
  if (parent.player == Player::ME)
    return Player::OPPONENT
  else
    return Player::ME
  end
end

def combine_predictions(player, preds)
  if (player == Player::OPPONENT)
    if (preds.empty?)
      return Prediction::WIN
    end
    return preds.max
  else
    if (preds.empty?)
      return Prediction::LOSE
    end
    return preds.min
  end
end

def print_tree_node(node)
  if (node.player == Player::ME)
    print "ME:"
  elsif (node.player == Player::OPPONENT)
    print "OP:"
  else
    print "DE:"
  end
  if (node.prediction == Prediction::WIN)
    print "WIN "
  elsif (node.prediction == Prediction::LOSE)
    print "LOS "
  else
    print "DEF "
  end
  print "<#{node.first_move}, #{node.second_move}> #{node}\t"

end

def bfs_prediction_tree(tree_root)
  fifo_q = [tree_root]
  current_level_count = 1
  while (!fifo_q.empty?)
    puts "current level count = #{current_level_count}"
    current = fifo_q.shift
    print_tree_node(current)
    # add all children to fifo queue
    #puts "children of #{current}: #{current.p_children}"
    current.p_children.each { |x| fifo_q.push(x) }
    #puts "fifo queue: #{fifo_q}"
    # level ends here
    current_level_count -= 1
    if (current_level_count == 0)
      print "\n"
      # calculate new level count
      # count the number of children of 
      # what's currently in fifo
      current_level_count = fifo_q.size
    end
  end
  print "\n"
end

ARGV.each do|a|
  puts "Argument: #{a}"
end
puts "Reading input file #{ARGV[0]}"

$total_num_moves = 0
$rounds_taken = 0
$move_combinations = []
last_move = -1
next_last_move = -1

counter = 1
round_counter = 1
begin
  file = File.new(ARGV[0], "r")
  while (line = file.gets)
    puts "#{counter}: #{line}"
    if (counter == 1)
      # first line
      # the number of total moves
      $total_num_moves = line.to_i
      for i in 0..$total_num_moves-1
        $move_combinations.push([])
        for j in 0..$total_num_moves-1
          $move_combinations[i].push(0)
        end
      end
    elsif (counter == 2)
      # second line
      # the number of rounds already taken
      $rounds_taken = line.to_i
    else 
      # a move combination line
      tmp = line.split(/ /)
      #puts tmp
      $move_combinations[tmp[0].to_i][tmp[1].to_i] = 1
      $move_combinations[tmp[1].to_i][tmp[0].to_i] = 1
      if (round_counter == $rounds_taken)
        next_last_move = tmp[0].to_i
        last_move = tmp[1].to_i
      end
      round_counter += 1
    end
    counter += 1
  end
  file.close
rescue => err
  puts "Exception: #{err}"
  err
end

puts "total number of moves = #{$total_num_moves}"
puts "#{$rounds_taken} of rounds have passed"
print_moves

tree_root = PredictionTreeNode.new
if ($rounds_taken%2 == 1)
  tree_root.player = Player::ME
else
  tree_root.player = Player::OPPONENT
end
tree_root.first_move =  next_last_move
tree_root.second_move = last_move
puts "before build"
print_tree_node(tree_root)
print "\n"
final_decision = build_prediction_tree(tree_root, last_move)
puts "after build"
print_tree_node(tree_root)
print "\n"
puts "print the whole tree"
bfs_prediction_tree(tree_root)
# output win or lose
if (final_decision == Prediction::WIN)
  puts "Win"
else 
  puts "Lose"
end
