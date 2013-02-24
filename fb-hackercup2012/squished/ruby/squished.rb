#!/usr/bin/env ruby

#########################
#      sub routines     #
#########################
def get_num_possible_messages m, message
  puts "m = #{m} message = #{message}" if $debug
  # initialize an array containing the possible 
  # number of messages in message[1:i]
  num_message_for_position = Array.new(message.size+1, 0)
  # another array to indicate
  # when an element in num_message_for_position 
  # can be used in computation
  valid_flags = Array.new(message.size+1, 1)
  if message[0] != 48 then # 48 is the ASCII code for '0'
    num_message_for_position[0] = 1
  else
    valid_flags[0] = 0
  end
  for i in (1..message.size)
    for j in (0..i-1)
      next if valid_flags[j] == 0
      puts "consider #{message[j, i-j]}" if $debug
      if message[j, i-j].to_i <= m and message[j, i-j].to_i >=1 then
        num_message_for_position[i] += num_message_for_position[j]
      else
        valid_flags[j] = 0
      end
    end
    puts "num_message_for_position : #{num_message_for_position.inspect}" if $debug
    puts "valid_flags : #{valid_flags.inspect}" if $debug
  end

  return num_message_for_position[-1]
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
  puts "test case #{i+1} : M = #{test_case_array[2*i]} message = #{test_case_array[2*i+1]}" if $debug
  num_messages = get_num_possible_messages test_case_array[2*i].to_i, test_case_array[2*i+1].to_s
  puts "Case ##{i+1}: #{num_messages % 4207849484}"
end

