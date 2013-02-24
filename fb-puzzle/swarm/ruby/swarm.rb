#!/usr/bin/env ruby

# Use 0/1 knap-sack to model the problem
# And solve the problem by Dynamic Programming

#########################
#      sub routines     #
#########################
def optimal_attack_plan_for_planet planet_id
  # number of bases on this planet
  num_bases = $num_bases_per_planet[planet_id]
  # total zerg force available
  total_z_force = $z_force_per_planet[planet_id]
  # max_mineral matrix
  # first row and column are all 0s 
  mm = Array.new
  # constrcut a max_mineral solution
  # first row and column are all 0s 
  mc = Array.new
  for k in (0..num_bases)
    mm.push Array.new(total_z_force+1, 0)
    mc.push Array.new(total_z_force+1, 0)
  end
  puts "#{mm.inspect}" if $debug
  puts "#{mc.inspect}" if $debug
  # compute mm from bottom up
  # mm[k][i] = max { mm[k-1][i-z[k]]+m[k], mm[k-1][i] }
  for k in (1..num_bases)
    for i in (1..total_z_force)
      prev_i = i - $min_z_force_per_base[planet_id][k]
      if prev_i < 0 then
        # zerg cannot take any minerals
        # if not enough zerg force
        current_max = 0
      else
        current_max = mm[k-1][prev_i] + $m_per_base[planet_id][k]
      end
      # in this case
      # we have picked base k
      mc[k][i] = 1
      if current_max<=mm[k-1][i] then
        # in this case
        # we don't pick base k
        # a subtlety here
        # if attacking base k dones't get us any more mineral
        # then don't attack
        # in this way, we can handle the case
        # where current_max and mm[k-1][i] are both 0s
        # which means we don't have enough zerg froce 
        # to take over any of the bases considered
        current_max = mm[k-1][i]
        mc[k][i] = 0
      end
      mm[k][i] = current_max
    end
  end
  
  if $debug then
    puts "max mineral matrix"
    for i in (1..num_bases)
      puts "base #{i}: #{mm[i].inspect}"
    end
    puts "max mineral construction matrix"
    for i in (1..num_bases)
      puts "base #{i} : #{mc[i].inspect}"
    end
  end
  
  # construct the optimal zerg force deployment
  # from matrix mc
  k = num_bases
  i = total_z_force
  total_z_force_deployed = 0
  total_mineral_captured = 0
  # base_id => zerg force deployed
  bases_attacked = Hash.new
  while k > 0 and i > 0
    if mc[k][i] == 1 then
      # base k is attacked
      total_z_force_deployed += $min_z_force_per_base[planet_id][k]
      total_mineral_captured += $m_per_base[planet_id][k]
      bases_attacked[k] = $min_z_force_per_base[planet_id][k]
      i -= $min_z_force_per_base[planet_id][k]
    end
    k -= 1
  end
  puts "total force deployed on planet #{planet_id} : #{total_z_force_deployed}" if $debug
  puts "deployment plan : #{bases_attacked.inspect}" if $debug
  
  # print out the solution
  # in a required form
  puts "#{total_z_force_deployed} #{total_mineral_captured}"
  tmp_str = ""
  bases_attacked.keys.sort.each { |k|
    tmp_str += "#{k-1} #{bases_attacked[k]} "
  }
  puts "#{tmp_str.chomp()}"
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
    # create a dummy base 0
    # this makes DP convinient
    $t_force_per_base[planet_id].push 0
    $m_per_base[planet_id].push 0
    $min_z_force_per_base[planet_id].push 0
    base_id += 1
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
  if base_id > $num_bases_per_planet[planet_id] then
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
for i in (0..($num_planets-1)) 
  optimal_attack_plan_for_planet i
end

#########################
#       validation      #
#########################
exit if !$debug

