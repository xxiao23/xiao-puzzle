#!/usr/bin/env ruby

#########################
#      sub routines     #
#########################
def get_least_steps_for_s s
  middle_path_num = Math.sqrt(s).floor
  puts "middle path num = #{middle_path_num}" if $debug
  least_steps = 10000000
  for index in (0..(middle_path_num-1))
    next if s % (middle_path_num - index) != 0
    alter_path_num = s / (middle_path_num - index)
    # next if !$s_value_map.has_key? middle_path_num-index or !$s_value_map.has_key? alter_path_num # skip if such path numbers are impossible
    if $s_value_map.has_key? middle_path_num-index then
      steps_part_1 = $s_value_map[middle_path_num - index]
    else 
      steps_part_1 = middle_path_num - index
    end
    if $s_value_map.has_key? alter_path_num then
      steps_part_2 = $s_value_map[alter_path_num]
    else
      steps_part_2 = alter_path_num
    end
    puts "considering path factorization : #{middle_path_num - index} x #{alter_path_num}" if $debug
    tmp_steps = steps_part_1 + steps_part_2
    if least_steps > tmp_steps then
      least_steps = tmp_steps
      puts "found less steps = #{least_steps}" if $debug
    end
  end
  return least_steps
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
# key is s value
# value is the point with min steps for this s value
$s_value_map = Hash.new
# a pyramid like matrix
# the top layer of the pyramid is the S value for (0, 0)
# the second layer is (0,1), and (1, 0)
#             (0,0)
#         (0,1)  (1,0)
#     (0,2)  (1,1)  (0,2)
#     .......................
$prev_step_arr = Array.new
$cur_step_arr = Array.new

#########################
#   process input file  #
#########################
input_file = File.new(input_file_name, 'r')
is_first_line = true
case_array = Array.new
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
end

# build hash table for s values
# for max s
# since it can be used in all test cases
max_steps = 0
case_array.each { |s|
  middle_path_num = Math.sqrt(s).floor
  for index in (0..(middle_path_num-1))
    next if s % (middle_path_num - index) != 0 # skip if middle_path_num-index is not a factor of s
    # factorize s = (middle_path_num-index) * (s/(middle_path_num-index))
    alter_path_num = s / (middle_path_num - index)
    tmp_max_steps = middle_path_num - index + alter_path_num
    if max_steps < tmp_max_steps then
      max_steps = tmp_max_steps
      puts "new max steps found = #{max_steps} for #{s}=#{middle_path_num - index}x#{alter_path_num}" if $debug
    end
    break
  end
}
puts "max steps to search = #{max_steps}" if $debug

$prev_step_arr = []
$cur_step_arr = []
current_steps = 4
while (current_steps <= max_steps)
  puts "current_steps = #{current_steps}" if $debug
  # prepend and append missing elements
  $prev_step_arr.insert(0, current_steps-1)
  # $prev_step_arr.push current_steps-1
  $prev_step_arr.insert(0, 1)
  # $prev_step_arr.push 1
  puts "prev_step_arr = #{$prev_step_arr.inspect}" if $debug
  for i in (2..(current_steps/2-1))
    puts "i = #{i}" if $debug
    tmp_value = 0
    if i-1 >= 0 then
      tmp_value += $prev_step_arr[i-1]
    end
    if i < current_steps and !$prev_step_arr[i].nil? then
      tmp_value += $prev_step_arr[i]
    elsif i < current_steps and $prev_step_arr[i].nil? then
      # the element in prev_step_arr must be greater than 10^7 in this case
      tmp_value = 10000002
    end
    break if tmp_value > 10000001 # the array is monotonically increasing; so need to proceed if found one > 10^7
    $cur_step_arr.push tmp_value
    if !$s_value_map.has_key? tmp_value and current_steps < tmp_value then
      puts "update s_value_map[#{tmp_value}] to #{current_steps}" if $debug
      $s_value_map[tmp_value] = current_steps
    else
      if $s_value_map[tmp_value] > current_steps
        puts "update s_value_map[#{tmp_value}] to #{current_steps}" if $debug
        $s_value_map[tmp_value] = current_steps
      end
    end
  end
  # deal with the half-point element
  if $prev_step_arr[-1].nil? then
    if current_steps % 2 == 0 then
      $cur_step_arr.push $prev_step_arr[-1] * 2
      tmp_value = $prev_step_arr[-1] * 2
    else
      $cur_step_arr.push($prev_step_arr[-1] + $prev_step_arr[-2])
      tmp_value = $prev_step_arr[-1] + $prev_step_arr[-2]
    end
    if !$s_value_map.has_key? tmp_value and current_steps < tmp_value then
      puts "update s_value_map[#{tmp_value}] to #{current_steps}" if $debug
      $s_value_map[tmp_value] = current_steps
    else
      if $s_value_map[tmp_value] > current_steps
        puts "update s_value_map[#{tmp_value}] to #{current_steps}" if $debug
        $s_value_map[tmp_value] = current_steps
      end
    end
  end
  current_steps += 1
  puts "cur_step_arr = #{$cur_step_arr.inspect}" if $debug
  break if $cur_step_arr.empty?
  $prev_step_arr.clear
  # copy current_step_arr to prev_step_arr
  $prev_step_arr = Array.new($cur_step_arr)
  $cur_step_arr.clear
end
print_s_value_map if $debug

case_array.each_index { |index|
  puts "working on s = #{case_array[index]}" if $debug
  least_steps = get_least_steps_for_s case_array[index]
  puts "Case ##{index+1}: #{least_steps}"
}