#!/usr/bin/env ruby

#########################
#      sub routines     #
#########################
def get_original_sequence n, debug_msg
  $debug_print_counter = 0 # reset coutner every time
  $debug_msg = debug_msg
  original_arr = Array.new
  for i in (0..(n-1))
    original_arr.push i
  end
  sequence = merge_sort original_arr
  sequence.each_index { |index|
    original_arr[sequence[index]] = index + 1
  }
  return original_arr
end

def get_checksum sequence
  result = 1
  for i in (0..(sequence.size-1))
    result = (31 * result + sequence[i]) % 1000003
  end
  return result
end

def merge_sort arr
  n = arr.size
  if n <= 1 then
    return arr
  end
  
  mid = n/2.floor
  puts "mid = #{mid}" if $debug
  first_half = merge_sort(arr[0..(mid-1)])
  second_half = merge_sort(arr[mid..(n-1)])
  return merge(first_half, second_half)
end

def merge arr1, arr2
  print  "merge #{arr1.inspect} and #{arr2.inspect} = " if $debug
  result = []
  while arr1.size > 0 and arr2.size >0
    $debug_print_counter += 1
    if $debug_msg[$debug_print_counter-1] == 49 then # arr1[0] < arr2[0]
      # print 1
      puts "debug counter = #{$debug_print_counter} print_number = #{$debug_msg[$debug_print_counter-1]} : should print 1" if $debug
      result.push(arr1[0])
      arr1.delete_at 0
    else
      # print 2
      puts "debug counter = #{$debug_print_counter} print_number = #{$debug_msg[$debug_print_counter-1]} : should print 2" if $debug
      result.push(arr2[0])
      arr2.delete_at 0
    end
  end
  result += arr1
  result += arr2
  puts "#{result.inspect}" if $debug
  return result
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
# count the number of times debug prints 1 or 2
$debug_print_counter = 0
$debug_msg = nil
$original_arr = nil

#########################
#   process input file  #
#########################
input_file = File.new(input_file_name, 'r')
is_first_line = true
test_case_array = Array.new
while line = input_file.gets
  if is_first_line then
    is_first_line = false
    $num_test_cases = line.chomp().to_i
    puts "number of test cases: #{$num_test_cases}" if $debug
    next
  end
  puts "#{line}" if $debug
  tmp = line.chomp.split(/\s+/)
  puts "#{tmp.inspect}" if $debug
  tmp.each { |e|
    test_case_array.push e
  }  
end

for i in (0..((test_case_array.size)/2-1))
  puts "test case #{i+1} : N = #{test_case_array[2*i]} debug_msg = #{test_case_array[2*i+1]}" if $debug
  original_arr = get_original_sequence test_case_array[2*i].to_i, test_case_array[2*i+1]
  puts "original array = #{original_arr.inspect}" if $debug
  checksum = get_checksum original_arr
  puts "Case ##{i+1}: #{checksum}"
end