#!/usr/bin/env ruby

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

# build a prediction tree
# the root node tells whether 
# this game can be won
def build_prediction_tree(tree_root_player, last_move)
  preds = []
  # try each possible dance
  for i in 0..$total_num_moves-1
    if ($move_combinations[last_move][i] == 0)
      new_tree_node_player = assign_player(tree_root_player)
      $global_count += 1
      # mark the combs as used
      $move_combinations[last_move][i] = 1
      $move_combinations[i][last_move] = 1
      # build a subtree
      prediction = build_prediction_tree(new_tree_node_player, i)
      if (new_tree_node_player == Player::ME && prediction == Prediction::WIN)
        $move_combinations[last_move][i] = 0
        $move_combinations[i][last_move] = 0
        return Prediction::WIN
      elsif (new_tree_node_player == Player::OPPONENT && prediction == Prediction::LOSE)
        $move_combinations[last_move][i] = 0
        $move_combinations[i][last_move] = 0
        return Prediction::LOSE
      end
      preds.push(prediction)
      # undo the comb marks
      $move_combinations[last_move][i] = 0
      $move_combinations[i][last_move] = 0
    end
  end

  tree_root_prediction = combine_predictions(tree_root_player, preds)
  return tree_root_prediction
end

def assign_player(parent_player)
  if (parent_player == Player::ME)
    return Player::OPPONENT
  else
    return Player::ME
  end
end

def combine_predictions(player, preds)
  if (player == Player::ME)
    if (preds.empty?)
      return Prediction::WIN
    end
    return preds.min
  else
    if (preds.empty?)
      return Prediction::LOSE
    end
    return preds.max
  end
end

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

#puts "total number of moves = #{$total_num_moves}"
#puts "#{$rounds_taken} of rounds have passed"
#print_moves

if ($rounds_taken%2 == 1)
  tree_root_player = Player::ME
else
  tree_root_player = Player::OPPONENT
end
final_decision = build_prediction_tree(tree_root_player, last_move)
# output win or lose
if (final_decision == Prediction::WIN)
  puts "Win"
else 
  puts "Lose"
end
