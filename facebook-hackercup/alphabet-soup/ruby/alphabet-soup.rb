#!/usr/bin/env ruby

#########################
#      sub routines     #
#########################
def get_hackercup_num word_array
  # create a map that stores the number of appearances
  # of each character in HACKERCUP
  char_count_map = Hash.new
  char_count_map["H"] = 0
  char_count_map["A"] = 0
  char_count_map["C"] = 0 # two 'C' is needed
  char_count_map["K"] = 0
  char_count_map["E"] = 0
  char_count_map["R"] = 0
  char_count_map["U"] = 0
  char_count_map["P"] = 0
  word_array.each { |word|
    word.each_char { |c|
      if char_count_map.has_key? c then
        char_count_map[c] += 1
      end
    }
  }
  char_count_map["C"] /= 2
  return char_count_map.values.sort[0]
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
$str = "HACKERCUP"

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
  hackercup_num = get_hackercup_num tmp
  puts "Case ##{case_num}: #{hackercup_num}"
  case_num += 1
end