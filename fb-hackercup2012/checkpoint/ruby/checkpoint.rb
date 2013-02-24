#!/usr/bin/env ruby

# define S(x,y) = the number of distinct shortest paths from (0,0) to (x,y)
# S(x,y) forms a pascal triangle
#                    <0,0>                           1
#                 <1,0> <0,1>                       1 1
#              <2,0> <1,1> <0,2>                   1 2 1
#           <3,0> <2,1> <1,2> <0,3>               1 3 3 1
#        <4,0> <3,1> <2,2> <1,3> <0,4>           1 4 6 4 1
#     <5,0> <4,1> <3,2> <2,3> <1,4> <0,5>       1 5 1 1 5 1
#                                                    0 0
# Now, before reaching (x,y), one needs to clear a checkpoint (xc,xy).
# This just splits the race into two parts: 1) from (0,0) to (xc,xy) and 2) from (xc,xy) to (x,y).
# Part 2 is equivalent to from (0,0) to (x-xc,y-yc).
# And the constraint of S(x,y) = T is equivalent to S(xc,xy) * S(x-xc,y-yc) = T, 0<=xc<=x, 0<=yc<=y.
# 
# Let's factorize T = t1 * t2,
# find the (x1,y1) such that S(x1,y1) = t1 and (x1+y1) is minimized;
# then find the (x2,y2) such that S(x2,y2) = t2 and (x2+y2) is minimized.
# The solution for (x,y) should be (x1+x2, y1+y2).
# And the minimal number of steps is x1+y1+x2+y2.
#
# ALGORITHM : 1) scan the pascal triangle to store the minimal number of steps required for S(x,y);
#             2) iterate through all possible factorizations, compute the minimal x1+y1+x2+y2 among all possible values.
#
# For Step 1, naively, if we scan all elements from level 0 to level T, it's guaranteed that all possible elements less than or equal
# to T are covered. However, this leads to runtime of O(T^2). T can be as large as 10^7. So it's crucial to narrow down the
# scan scope in the pascal triangle, to make an scalable solution for large T.
#
# For Step 2, we just have to scan all possible factorizations of T, starting from sqrt(T). Its runtime is O(sqrt(T)) then.
# We can bear with that for now. Furthur optimization might be possible.

#########################
#      sub routines     #
#########################
def get_least_steps_for_s s
  s_sqrt = Math.sqrt(s).floor
  puts "square root of #{s} = #{s_sqrt}" if $debug
  least_steps = 10000000
  for index in (0..(s_sqrt-1))
    next if s % (s_sqrt - index) != 0
    # find a factorization
    factor_1 = s_sqrt - index
    factor_2 = s / factor_1
    if $s_value_map.has_key? factor_1
      num_steps_1 = $s_value_map[factor_1]
    else
      num_steps_1 = factor_1
    end
    if $s_value_map.has_key? factor_2
      num_steps_2 = $s_value_map[factor_2]
    else
      num_steps_2 = factor_2
    end
    puts "considering factorization : #{factor_1} x #{factor_2} with min steps #{num_steps_1} + #{num_steps_2}" if $debug
    tmp_steps = num_steps_1 + num_steps_2
    if least_steps > tmp_steps then
      least_steps = tmp_steps
      puts "find less steps = #{least_steps}" if $debug
    end
  end
  return least_steps
end

def build_s_value_map s
  prev_level_array = [3,1]
  cur_level_array = []
  cur_level = 4 # start from level 4, levels 0 to 3 are trivial
  
  # reach the first level whose 3rd smallest element (the smallest is 1 and 2nd smallest is cur_level)
  # is equal than or larger than s.
  # 3rd element can be nil though, 
  # level 3 only contains 2 elements in the right half.
  while prev_level_array[-3].nil? || prev_level_array[-3] < s
    # if current level is even number,
    # the prev level is odd,
    # prepend prev level with its first element
    # since that element appears twice and is useful in calculate current level
    if (cur_level % 2 == 0) 
      prev_level_array.insert 0, prev_level_array.first
    end
    # iterate through the prev level
    # and calculate the cur level 
    for i in 0..(prev_level_array.size-3)
      cur_s_value = prev_level_array[i]+prev_level_array[i+1]
      cur_level_array.push cur_s_value
      # replace s_value_map value for cur_s_value
      # if cur_s_value has not been stored
      # or the stored value is larger
      if cur_s_value <= s && (!$s_value_map.has_key? cur_s_value || $s_value_map[cur_s_value] > cur_level)
        $s_value_map[cur_s_value] = cur_level
      end
    end
    # append current level with the tailing cur_level and 1
    cur_level_array.push cur_level
    cur_level_array.push 1
    # 
    prev_level_array = cur_level_array.clone
    cur_level_array.clear
    cur_level += 1
    puts "level #{cur_level} : #{prev_level_array.inspect}" if $debug
  end
end

def print_s_value_map
  $s_value_map.keys.sort.each { |key|
    puts "#{key} : #{$s_value_map[key].inspect}"
  }
end

#########################
#    process arguments  #
#########################
if ARGV[0].nil?
  raise "missing input file"
end

input_file_name = ARGV[0]

if !ARGV[1].nil? and ARGV[1] == "debug" then
  $debug = true
else
  $debug = false
end

if !ARGV[2].nil? then
  $debug_level = ARGV[2].to_i
end

puts "debug : #{$debug}" if $debug

#########################
#    global variables   #
#########################
# key is the value of S(x,y)
# value is the point with min steps for S(x,y)
# the hash table is lazily instantiated
# new element is inserted into the hash first time it's encountered
$s_value_map = Hash.new
$s_value_map[0] = 0

#########################
#   process input file  #
#########################
input_file = File.new(input_file_name, 'r')
is_first_line = true
case_array = Array.new
max_s = 0
while line = input_file.gets
  if is_first_line then
    is_first_line = false
    $num_test_cases = line.chomp().to_i
    puts "number of test cases: #{$num_test_cases}" if $debug
    next
  end
  tmp = line.chomp().to_i
  puts "working on s=#{tmp.inspect}" if $debug
  case_array.push tmp
  if tmp > max_s 
    max_s = tmp
  end
end

#########################
#   main process        #
#########################

# build S value hash map
puts "max s = #{max_s}" if $debug
# abort("debug")
build_s_value_map max_s
print_s_value_map if $debug

case_array.each_index { |index|
  puts "working on s = #{case_array[index]}" if $debug
  least_steps = get_least_steps_for_s case_array[index]
  puts "Case ##{index+1}: #{least_steps}"
}