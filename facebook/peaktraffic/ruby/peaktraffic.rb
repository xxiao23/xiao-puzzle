#!/usr/bin/env ruby

require 'set'

# Use Bron-Kerbosch algorithm
# for finding maximal cliques in an undirected graph
# http://en.wikipedia.org/wiki/Bron-Kerbosch_algorithm

#########################
#      sub routines     #
#########################
def bron_kerbosch compsub, candidates, nots, level
  return if level == $stop_level and $debug
  print "level#{level} " if $debug
  for i in (0..level) 
    print "  "
  end if $debug
  print "BronKerbosch (#{compsub.inspect}, #{candidates.inspect}, #{nots.inspect})" if $debug 
  if candidates.empty? and nots.empty? then
    $maximal_cliques[compsub.first] = compsub
    print " : output #{compsub.inspect}\n"
    return
  end
  print "\n" if $debug
  while !candidates.empty?
    v = candidates.first
    new_compsub = SortedSet.new(compsub).add v
    new_candidates = SortedSet.new(candidates).intersection neighbor(v)
    new_nots = SortedSet.new(nots).intersection neighbor(v)
    bron_kerbosch new_compsub, new_candidates, new_nots, level+1
    candidates = candidates.delete v
    nots = nots.add v
  end
end

def neighbor v
  return $adj_map[v]
end

def dump_adj_map
  $adj_map.each { |key, value| 
    print "adjacent to #{key} :\t"
    value.each { |vertex|
      print "#{vertex}\t"
    }
    puts ""
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
# adjacency map of the directed graph
# key = id of the vertex
# value = a set of adjacent vertices
$adj_map = Hash.new
# a set of maximal cliques
# key = the first vertex in the maximal clique as a sorted set
# value = the sorted set of vertice in the maximal clique
$maximal_cliques = Hash.new 

#########################
#   process input file  #
#########################
begin
  input_file = File.new(input_file_name, "r")
  while line = input_file.gets
    puts "#{line}" if $debug
    tmp = line.chomp.split(/\t/) # time_stap from to
    puts "#{tmp}" if $debug
    from = tmp[1]
    to = tmp[2]
    if !$adj_map.has_key? from then
      $adj_map[from] = Set.new
    end
    $adj_map[from].add to
  end
rescue => err
  puts "Exception: #{err}"
  err
end

puts "initial graph" if $debug
dump_adj_map if $debug

# if the edge is only one directional
# remove them
# keep only bi-birectional edges
$adj_map.each { | key, value | 
  value.each { | vertex |
    value.delete vertex if !$adj_map.has_key? vertex or !$adj_map[vertex].include? key
  }
}
puts "graph after pruning" if $debug
dump_adj_map if $debug

#########################
#       main process    #
#########################

# auxiliary variables
compsub = SortedSet.new # nodes already a part of the clique
candidates = SortedSet.new # nodes connected with all nodes of compsub
nots = SortedSet.new # nodes already processed which lead to a valid extensions for compsub and shouldn't be touched

# initially all vertice in the graph are candidates
$adj_map.keys.each { | v | candidates.add v }

# call KB algo
bron_kerbosch compsub, candidates, nots, 0

# print out maximal cliques
$maximal_cliques.keys.sort.each { | k | 
  $maximal_cliques[k].each { |v| print "#{v} " }
  print "\n"
}

#########################
#       validation      #
#########################
return if !$debug

# check if each set is a clique

# check if each clique is maximal