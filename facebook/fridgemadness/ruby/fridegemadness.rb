#!/usr/bin/env ruby

######################################
#           global variables         #
######################################

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
# paired engineers
# each pairing has two (key, value) tuples
# high_engineer => low_engineer
# low_engineer => high_enginner
$engineer_pairs = Hash.new 

# auxiliary variables
$line_num = 0
$line_engineers = 0
$line_drinks = 0

######################################
#          class definitions         #
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

# linearly scan all forward edges
# in the residue network to
# find the one augmenting_path.
# return nil if not found.
def find_augmenting_path(g, gf, num_nodes)
  # augmenting_path = [high_enginner, low_engineer, residue_capacity]
  ap = [-1, -1, -1] # initial value
  for h in (0..(num_nodes/2-1)) # high engineer
    for l in ((num_nodes/2)..(num_nodes-1)) # low engineer
      if gf[h][l] > ap[2] then
        tmp_ap = [h, l, gf[h][l]]
        ap = tmp_ap if is_augmenting_path_valid? g, tmp_ap
      elsif gf[h][l] == ap[2] then
        if h < ap[0] then
          # prefer smaller indexed high engineer (better skilled)
          tmp_ap = [h, l, gf[h][l]]
          ap = tmp_ap if is_augmenting_path_valid? g, tmp_ap
        elsif h == ap[0] and l > ap[1] then
          # for the same high engineer
          # larger indexed low engineer is preferred
          tmp_ap = [h, l, gf[h][l]]
          ap = tmp_ap if is_augmenting_path_valid? g, tmp_ap
        end
      end
    end # for l
  end # for h
  return nil if ap[0] == -1
  return ap
end

# return true if an augmenting path is valid
# return false otherwise
def is_augmenting_path_valid?(g, ap)
  # nil path
  if ap.nil? then
    return false
  end
  
  h = ap[0]
  l = ap[1]
  residue_capacity = ap[2]
  # no residue capacity
  if residue_capacity <=0 then
    return false
  end
  # if h is already paired
  # make sure l is more preferred than h's current partner
  if $engineer_pairs.has_key? h then
    # not valid if new preference score is lower
    return false if $adj[h][l] < $adj[h][$engineer_pairs[h]]
    # not valid if the score is the same but the new partner has smaller id
    return false if $adj[h][l] == $adj[h][$engineer_pairs[h]] and l < $engineer_pairs[h]
  end
  # if l is already paired 
  # make sure h is more preferred than l's current partner
  if $engineer_pairs.has_key? l then
    # not valid if new preference score is lower
    return false if $adj[l][h] < $adj[l][$engineer_pairs[l]]
    # not valid if the score is the same but the new partner has smaller id
    return false if $adj[l][h] == $adj[l][$engineer_pairs[l]] and h < $engineer_pairs[l]
  end
  # none of above, then it's a valid augmenting path
  return true
end

# update flow network by adding flow along the augmenting path
def update_flow_network(g, ap, num_nodes)
  h = ap[0]
  l = ap[1]
  residue_capacity = ap[2]
  # if h or l is paired
  # delete their pairing recrods
  # and store the value from h to its partner
  # or the value from l to its partner
  #
  # NOTE: h and l cannot be partner already
  # otherwise, edge h->l in residue network will not
  # have positive capacity
  old_h_flow = 0
  old_h_partner_flow = 0
  old_l_flow = 0
  old_l_partner_flow = 0
  if $engineer_pairs.has_key?(h) then
    old_h_partner = $engineer_pairs[h]
    old_h_flow = $adj[h][old_h_partner]
    old_h_partner_flow = $adj[old_h_partner][h]
    # update edges going into h's old partner
    # by remove old_h_partner_flow
    for i in (0..(num_nodes/2-1))
      g[i][old_h_partner].f -= old_h_partner_flow
    end
  end
  if $engineer_pairs.has_key?(l) then
    old_l_partner = $engineer_pairs[l]
    old_l_flow = $adj[l][old_l_partner]
    old_l_partner_flow = $adj[old_l_partner][l]
    # update edges coming out from l's old partner
    # by removing old_l_partner_flow
    for j in ((num_nodes/2)..(num_nodes-1))
      g[old_l_partner][j].f -= old_l_partner_flow
    end
  end
  # now safe to remove obselete pairings
  $engineer_pairs.delete_if { |key, value| [h, l].include? key or [h, l].include? value }
  # record the new pairing of h with l
  $engineer_pairs[h] = l
  $engineer_pairs[l] = h
  # update edges coming out from h
  # by replacing old_h_flow with adj[h][l]
  for j in ((num_nodes/2)..(num_nodes-1))
    g[h][j].f -= old_h_flow
    g[h][j].f += $adj[h][l]
  end
  # update edges going into l
  # by replacing old_l_flow with adj[l][h]
  for i in (0..(num_nodes/2-1))
    g[i][l].f -= old_l_flow
    g[i][l].f += $adj[l][h]
  end
end

# update residue network
# cf(i,j) =  c(i,j) - f(i,j) if (i,j) in E
#            f(j, i)         if (j,i) in E
#            0               otherwise  
def update_residue_network(g, gf, num_nodes)
  for i in (0..(num_nodes/2-1))
    for j in ((num_nodes/2)..(num_nodes-1))
      if g[i][j].f <= g[i][j].c then
        gf[i][j] = g[i][j].c - g[i][j].f
        gf[j][i] = g[i][j].f
      else
        gf[i][j] = 0
        gf[j][i] = 0
      end
    end
  end
end

def dump_networks
  puts "adjacency matrix"

  print "   "
  for i in (0..($num_engineers-1))
    print "#{i}\t"
  end
  print "\n"

  $adj.each_index { |i| 
    print "#{i}  "
    $adj[i].each { |s| print "#{s}\t" }
    print "\n" 
  }
  print "\n"

  puts "flow network"
  print "   "
  for i in (0..($num_engineers-1))
    print "#{i}\t"
  end
  print "\n"
  
  $flow_network.each_index { |i| 
    print "#{i}  "
    $flow_network[i].each { |s| print "#{s.f}/#{s.c}\t" }
    print "\n" 
  }
  print "\n"

  puts "residue network"
  print "   "
  for i in (0..($num_engineers-1))
    print "#{i}\t"
  end
  print "\n"

  $residue_network.each_index { |i| 
    print "#{i}  "
    $residue_network[i].each { |s| print "#{s}\t" }
    print "\n" 
  }
  print "\n"
end

def dump_engineer_pairs
  for i in (0..($num_engineers-1))
    puts "#{i} #{$engineer_pairs[i]}" if $engineer_pairs.has_key? i
  end
end
######################################
#       process input file           #
######################################

if (ARGV[1] == "debug")
  $debug = 1
else
  $debug = 0
end

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

# auxiliary variables
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
      else
        # this is backward edge
        # so add it to the total edge capacity
        $flow_network[j][i].c += my_score
        $flow_network[i].push FlowNetworkNode.new(0, 0)
        $residue_network[j][i] += my_score
        $residue_network[i].push 0
      end
    end
  end
end

puts "initial conditions" if $debug == 1
dump_networks if $debug == 1

######################################
#             main process           #
######################################

ap = [-1, -1, -1]
# loop until no augmenting path in residue network
counter = 0
while true
  # find an augmenting path
  ap = find_augmenting_path $flow_network, $residue_network, $num_engineers
  # finish if no augementing path is found
  break if !is_augmenting_path_valid? $flow_network, ap
  puts "augmenting path #{ap}" if $debug == 1
  # update flow network and record the paried engineers
  update_flow_network $flow_network, ap, $num_engineers
  # update residue network
  update_residue_network $flow_network, $residue_network, $num_engineers
  # print out networks if debug
  counter += 1
  puts "iteration #{counter}" if $debug == 1
  dump_networks if $debug == 1
  dump_engineer_pairs if $debug == 1
end

# print out engineer pairs order by high engineer ids
puts "final paring" if $debug == 1
for i in (0..($num_engineers-1))
  puts "#{i} #{$engineer_pairs[i]}"
end

# in debug mode
# do brutal force verification
exit if $debug == 0
puts "validation!!"
found_violation = 0
$engineer_pairs.each { |h, l|
  found_violation = 0
  puts "e#{h} -> e#{l}"
  if h >= $num_engineers/2 then
    puts "skip"
    next
  end
  # see if exist h->ll
  for ll in ($num_engineers/2..$num_engineers-1)
    next if ll == l
    hh = $engineer_pairs[ll]
    if ($adj[h][l] < $adj[h][ll] or ($adj[h][l] == $adj[h][ll] and l < ll)) and ($adj[ll][h] > $adj[ll][hh] or ($adj[ll][h] == $adj[ll][hh] and h > hh))
      puts "VIOLATION e#{h}->e#{l} : better choice e#{h}->e#{ll}"
      puts "scores : e#{h}->e#{l}=#{$adj[h][l]} e#{h}->e#{ll}=#{$adj[h][ll]} e#{ll}->e#{h}=#{$adj[ll][h]} e#{ll}->e#{hh}=#{$adj[ll][hh]}"
      found_violation = 1
      puts "new pairings e#{h}<->e#{ll} e#{hh}->e#{l}"
      break
    end
  end
  next if found_violation == 1
  # see if exist hh->l
  for hh in (0..$num_engineers/2-1)
    next if hh == h
    ll = $engineer_pairs[hh]
    if ($adj[l][h] < $adj[l][hh] or ($adj[l][h] == $adj[l][hh] and h < hh)) and ($adj[hh][l] > $adj[hh][ll] or ($adj[hh][l] == $adj[hh][ll] and l > ll))
      puts "VIOLATION e#{h}->e#{l} : better choice e#{hh}->e#{l}"
      puts "scores : e#{l}->e#{h}=#{$adj[l][h]} e#{l}->e#{hh}=#{$adj[l][hh]} e#{hh}->e#{l}=#{$adj[hh][l]} e#{hh}->e#{ll}=#{$adj[hh][ll]}"
      found_violation = 1
      puts "new pairings e#{hh}<->e#{l} e#{h}<->e#{ll}"
      break
    end
  end
}

puts "NO VIOLATIONS!" if found_violation == 0