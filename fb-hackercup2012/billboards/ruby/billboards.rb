#!/usr/bin/env ruby

#########################
#      sub routines     #
#########################
def get_max_font_size input_array
  upper_bound = [input_array[0].to_i, input_array[1].to_i].min
  lower_bound = 1
  max_font_size = 0
  while lower_bound <= upper_bound
    current_font_size = (upper_bound + lower_bound) / 2
    if can_font_size_fit! input_array, current_font_size then
      # current font size can fit
      # search its right half
      max_font_size = current_font_size if current_font_size > max_font_size
      lower_bound = current_font_size + 1
    else
      # current font size won't fit
      # search its left half
      upper_bound = current_font_size - 1
    end
  end
  return max_font_size
end

def can_font_size_fit! input_array, font_size
  num_lines_used = 1 # start with 1 to compensate the last line
  current_empty_space = input_array[0].to_i
  num_lines = input_array[1].to_i / font_size
  row_space = input_array[0].to_i
  index = 2
  while index < input_array.size and num_lines_used <= num_lines
    if current_empty_space >= input_array[index].length * font_size
      # the current line can enough space to accomodate the word
      current_empty_space -= (input_array[index].length + 1) * font_size # 1 for space
      index += 1
    else
      num_lines_used += 1 # move to the next line
      current_empty_space = row_space
    end
  end
  return num_lines_used <= num_lines
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
# number of test cases
$num_test_cases = 0

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
  max_font_size = get_max_font_size tmp
  puts "Case ##{case_num}: #{max_font_size}"
  case_num += 1
end
