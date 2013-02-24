#!/usr/bin/ruby

class Item
  attr_accessor :sku, :weight, :cost

  def initialize(sku, weight, cost)
    @sku = sku
    @weight = weight
    @cost = cost
  end

end

if (ARGV[1])
  $debug = 1
else
  $debug = 0
end

def least_cost_for_weight_recursive(weight)
  puts "calculating least cost for w=#{weight}" unless $debug==0
  $num_recursive_calls += 1
  if weight<=0
    $weight_cost_hash[weight] = 0
    return 0
  end
  min_cost = 2**64
  $item_array.each { |item|
    working_weight = weight - item.weight      
    $weight_cost_hash[working_weight] = least_cost_for_weight(working_weight) unless $weight_cost_hash.has_key?(working_weight)
    current_cost = item.cost + $weight_cost_hash[working_weight]
    if current_cost < min_cost
      min_cost = current_cost
    end
  }
  return min_cost
end

def least_cost_for_weight_iterative weight
  puts "calculating least cost for w=#{weight} iteratively" if $debug==1
  # create a array of size weight+1
  # the first element is always 0
  min_cost_vector = Array.new(weight+1, 0)
  # compute bottom-up
  for i in (1..weight)
    local_min_cost = min_cost_vector[i-1] + $most_item_cost
    # need to try each type of items
    # since each type has unlimited amount
    $item_array.each { |item|
      tmp_index = i - item.weight
      tmp_index = 0 if tmp_index < 0
      my_cost = min_cost_vector[tmp_index] + item.cost
      local_min_cost = my_cost if local_min_cost > my_cost
    }
    min_cost_vector[i] = local_min_cost
    puts "wegiht = #{i} : min cost = #{min_cost_vector[i]}" if $debug==1
  end
  # the last element in min_cost_vector is the solution
  # for the problem
  return min_cost_vector[weight]
end

######################################
# main #
######################################
$item_array = Array.new
$line_count = 0
$total_weight = 0
$weight_cost_hash = {}
$num_recursive_calls = 0
$most_item_cost = 0
begin
  file = File.new(ARGV[0], "r")
  while (line = file.gets)
    $line_count += 1
    if $line_count==1
      $total_weight = line.to_i
    else
      tmp = line.split(/\s+/)
      item = Item.new(tmp[0], tmp[1].to_i, tmp[2].to_i)
      $item_array.push item
      $most_item_cost = tmp[2].to_i if tmp[2].to_i > $most_item_cost
    end
  end
  file.close
rescue => err
  puts "Exception: #{err}:"
  err
end

puts "total weight #{$total_weight}" unless $debug==0
if $debug==1
  $item_array.each { |item| 
    puts item.sku, item.weight, item.cost }
end
puts "most item cost = #{$most_item_cost}" if $debug==1

puts least_cost_for_weight_iterative($total_weight)
puts "num of recursive calls #{$num_recursive_calls}" unless $debug==0