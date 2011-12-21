#!/usr/bin/env ruby

# Use 0/1 knap-sack to model the problem
# And solve the problem by Dynamic Programming

#########################
#      sub routines     #
#########################
def optimal_attack_plan_for_planet planet_id
  
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
# number of planets in the problem
$num_planets = 0
# an array, an element is the number of bases on that planet
$num_bases_per_planet = nil
# an array, an elemet is the total zerg force for that planet
$z_force_per_planet = nil
# an 2D array, an element is a 1D array containing Terran forces stationed in each base on that planet
$t_force_per_base = nil
# an 2D array, an element is a 1D array containing minerals in each base on that planet
$m_per_base = nil
# an 2D array, an element is the minimum zerg force required to capture minerals
$min_z_force_per_base = nil

#########################
#   process input file  #
#########################
input_file = File.new(input_file_name, "r")
num_bases_line = false
planet_id = 0
base_id = 0
line_no = 0
while line = input_file.gets
  line_no += 1
  puts "ln#{line_no}\t#{line}" if $debug
  # this is the line indicating the number of planets
  if line_no == 1 then
    $num_planets = line.chomp().to_i
    num_bases_line = true
    $num_bases_per_planet = Array.new($num_planets, 0)
    puts "#{$num_bases_per_planet.inspect}" if $debug
    $z_force_per_planet = Array.new($num_planets, 0)
    puts "#{$z_force_per_planet.inspect}" if $debug
    $t_force_per_base = Array.new
    $m_per_base = Array.new
    $min_z_force_per_base = Array.new
    for i in (1..$num_planets)
      $t_force_per_base.push Array.new
      $m_per_base.push Array.new
      $min_z_force_per_base.push Array.new
    end
    puts "#{$t_force_per_base.inspect}" if $debug
    puts "#{$m_per_base.inspect}" if $debug
    puts "#{$min_z_force_per_base.inspect}" if $debug
    next
  end
  # this is the line indicating the number of bases in the planet
  if num_bases_line then
    tmp = line.chomp().split(/\s+/)
    puts "planet id = #{planet_id}:\t#{tmp.inspect}" if $debug
    $num_bases_per_planet[planet_id] = tmp[0].to_i
    $z_force_per_planet[planet_id] = tmp[1].to_i
    num_bases_line = false
    next
  end
  # now this is a line for a specific base: t_force mineral
  tmp = line.chomp().split(/\s+/)
  puts "planet id = #{planet_id}:\t base #{tmp.inspect}" if $debug
  $t_force_per_base[planet_id].push tmp[0].to_i
  $m_per_base[planet_id].push tmp[1].to_i
  $min_z_force_per_base[planet_id].push(((Math::log(tmp[1].to_f-1) - 63*tmp[0].to_f + 10) / -21).ceil)
  puts "t force #{$t_force_per_base.inspect}" if $debug
  puts "mineral #{$m_per_base.inspect}" if $debug
  # start from next line are for next planet
  base_id += 1 
  if base_id == $num_bases_per_planet[planet_id] then
    num_bases_line = true
    planet_id += 1
    base_id = 0
    puts "next line starts a new planet : id = #{planet_id}" if $debug
  end
end


if $debug then
  puts "z force per planet\t:\t#{$z_force_per_planet.inspect}"
  puts "t force per base\t:\t#{$t_force_per_base.inspect}"
  puts "mineral per base\t:\t#{$m_per_base.inspect}"
  puts "min z force per base\t:\t#{$min_z_force_per_base.inspect}"
end

#########################
#       main process    #
#########################

#########################
#       validation      #
#########################
return if !$debug

