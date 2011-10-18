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

def least_cost_for_weight(weight)
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

######################################
# main #
######################################
$item_array = Array.new
$line_count = 0
$total_weight = 0
$weight_cost_hash = {}
$num_recursive_calls = 0
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

puts least_cost_for_weight($total_weight)
puts "num of recursive calls #{$num_recursive_calls}" unless $debug==0