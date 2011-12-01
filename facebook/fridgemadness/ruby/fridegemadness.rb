#!/usr/bin/env ruby

######################################
#             class definitions      #
######################################

# flow network node
class FlowNetworkNode 
  attr_accessor :f, :c # f for flow value; c for edge capacity
  
  def initialize(f, c)
    @f = f
    @c = c
  end
end

######################################
#             sub routines           #
######################################
# compute score for engineer_a -> engineer_b
def get_score(a, b)
  puts "score engineer_#{a} -> engineer_#{b}" unless $debug == 0
  hash_a = $preference_hash_array[a]
  hash_b = $preference_hash_array[b]
  score = 0
  hash_a.each do |drink_id, prefer_score|
    puts "drink #{drink_id} : score #{prefer_score}" unless $debug == 0
    if prefer_score == hash_b[drink_id]
      score += prefer_score**2
    elsif !hash_b.has_key?(drink_id) or prefer_score > hash_b[drink_id]
      score += prefer_score
    end
  end
  return score
end

# find an augmenting path in the residue network
# return nil if not found
def find_augmenting_path(gf)
  return nil
end

def update_flow_network()
end
######################################
#       process input file           #
######################################
if (ARGV[1] == "debug")
  $debug = 1
else
  $debug = 0
end

# adjacency matrix based on engineer preferences
$adj = Array.new
# list of drinks in the order from input file
$drink_preferences = Array.new 
# Hash: drink_id => preference_score
$preference_hash_array = Array.new 
# adjacency matrix for flow network
# all edges pointing from high engineers to low engineers
# edge_capacity(i, j) = adj[i][j] + adj[j][i] 
$flow_network = Array.new
# adjacency matrix for residue network
$residue_network = Array.new 

# auxiliary variables
$line_num = 0
$line_engineers = 0
$line_drinks = 0

# process input file
begin
  file = File.new(ARGV[0], "r")
  while line = file.gets
    $line_num += 1
    if $line_num == 1
      tmp = line.split(/\s+/)
      $line_drinks = 1 + tmp[1].to_i
      $line_engineers = $line_drinks + tmp[0].to_i
    end
    # read in drink info
    if $line_num > 1 and $line_num <= $line_drinks
      #puts line
    end
    # read in engineer preference info
    if $line_num > $line_drinks
      tmp = line.split(/\s+/)
      # first number is engineer id
      engineer_id = tmp[0].to_i
      tmp2 = tmp[1].split(/,/)
      n = tmp2.length # number of drinks
      $drink_preferences.push(Array.new)
      $preference_hash_array.push(Hash.new)
      tmp2.each_index { |i| 
        $drink_preferences[engineer_id].push(tmp2[i])
        $preference_hash_array[engineer_id][tmp2[i]] = n-i
      }
    end
  end
rescue => err
  puts "Exception: #{err}:"
  err
end

if ($debug == 1) 
  $drink_preferences.each_index { |x|
    print "engineer #{x} : "
    $drink_preferences[x].each { |e| print "#{e} " }
    print "\n"
    $preference_hash_array[x].each { |k, v| print "#{k}->#{v} "}
    print "\n"
  }
end

# global variables
$num_engineers = $line_engineers - $line_drinks
$half_percentile_index = $num_engineers / 2 - 1
puts "50 percentile engineer #{$half_percentile_index}" if $debug == 1

# fill in adj table O(n^2)
# build up a forward priority queue and a reverse priority queue
# for each lower engineer
for i in (0..$num_engineers-1)
  $adj.push Array.new
  $flow_network.push Array.new
  $residue_network.push Array.new
  for j in (0..($num_engineers-1))
    puts "i = #{i}; j = #{j}" if $debug == 1
    if (i<=$half_percentile_index and j<=$half_percentile_index) or (i>$half_percentile_index and j>$half_percentile_index)
      $adj[i].push 0
      $flow_network[i].push FlowNetworkNode.new(0, 0)
      $residue_network[i].push 0
    else 
      my_score = get_score(i, j)
      $adj[i].push my_score
      if (i<=$half_percentile_index) then
        # this is forward edge
        $flow_network[i].push FlowNetworkNode.new(0, my_score)
        $residue_network[i].push my_score
        puts "flow network : #{$flow_network}" if $debug == 1
        puts "residue network : #{$residue_network}" if $debug == 1
      else
        # this is backward edge
        # so add it to the total edge capacity
        puts "#{$flow_network[j][i]}" if $debug == 1
        $flow_network[j][i].c += my_score
        $flow_network[i].push FlowNetworkNode.new(0, 0)
        $residue_network[j][i] += my_score
        $residue_network[i].push 0
      end
    end
  end
end

puts "adjacency matrix" if $debug == 1
if $debug == 1
  print "   "
  for i in (0..($num_engineers-1))
    print "#{i}\t"
  end
  print "\n"
end
$adj.each_index { |i| 
  print "#{i}  "
  $adj[i].each { |s| print "#{s}\t" }
  print "\n" 
} unless $debug == 0
print "\n" unless $debug == 0

puts "intiail flow network" if $debug == 1
if $debug == 1
  print "   "
  for i in (0..($num_engineers-1))
    print "#{i}\t"
  end
  print "\n"
end
$flow_network.each_index { |i| 
  print "#{i}  "
  $flow_network[i].each { |s| print "#{s.f}/#{s.c}\t" }
  print "\n" 
} if $debug == 1
print "\n" if $debug == 1

puts "initial residue network" if $debug == 1
if $debug == 1
  print "   "
  for i in (0..($num_engineers-1))
    print "#{i}\t"
  end
  print "\n"
end
$residue_network.each_index { |i| 
  print "#{i}  "
  $residue_network[i].each { |s| print "#{s}\t" }
  print "\n" 
} if $debug == 1
print "\n" if $debug == 1

######################################
#             main process           #
######################################

# loop until no augmenting path in residue network
  # find an augmenting path

  # update flow network and record the paried engineers
  
  # update residue network