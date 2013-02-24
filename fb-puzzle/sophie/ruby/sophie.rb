#!/usr/bin/env ruby

require 'set'

# A valid search path is a path that must visit all nodes,
# which ensures Sophie to be found.
# If no such path exsits, no solution exists.
# Such a path can visit its last node once,
# and other nodes (including the starting one) at least once.
#
# When a node is visited for the first time,
# the expected time to find Sophie on that node is equal
# to the node's discovery probability times the sum of the lengths of 
# the edges that have been traversed so far.
# 
# This program uses a depth-first search like algorithm
# to search for all valid search paths and compute the expected discovery
# time of each valid search path.
# 
# A key observation is that a valid search path
# is made up by a concatenation of 
# n-1 simple paths with n endpoints.
# The first endpoint must s_0.
# Furthermore, the first k simple paths
# can only consist of the first k+1 endpoints,
# i.e., for s_0 -> s_1 -> s_2,
# the two simple paths s_0 -> s_1 and s_1 -> s_0
# only contain s_0, s_1 and s_2.
#
# The algorithm explores a possible route
# by going depth first in a route 
# until it visits all nodes.
# Based on the above observation,
# the set of routes considered to be explored
# from vertex s_k is the set of nodes
# that are reachable from s_k-1
# in the sub-graph consisting of
# only vertice s_0 to s_k. 

#########################
#      sub routines     #
#########################
# use Dijkstra's single-source shortest path algorithm 
# to find a set of reachcable vertice from vertex s
# subgraph is a Set containing all vertice id's belonging
# to the search graph
# return predecessor tree and shortest distances
def Dijkstra subgraph, u, depth
  # initialize shortest distance estimates and predecessor tree
  d = Hash.new
  pi = Hash.new
  subgraph.each { |v| 
    d[v] = Float::MAX
    pi[v] = -1
  }
  d[u] = 0
  # initialize the set of concluded vertice
  s = Hash.new
  # initialize the set of candidate vertice
  # as a priority queue on v.d
  q = d
  puts "depth = #{depth}\tinitial S = #{s.inspect} Q = #{q.inspect}" if $debug
  # loop to update d
  # loop invariant subgraph = s + q
  while !q.empty?
    min_d = q.values.min
    min_u = q.index min_d
    s[min_u] = min_d
    puts "depth = #{depth}\t(min_u, min_d) = (#{min_u}, #{min_d}) S = #{s.inspect} Q = #{q.inspect} adj[#{min_u}] = #{$adj[min_u].inspect}" if $debug
    # relax shortest distance estimates 
    # for each of min_d's adjacent vertice
    $adj[min_u].each_index { |v|
      if q.has_key? v and $adj[min_u][v] > -1 and q[v] > q[min_u] + $adj[min_u][v] then
        q[v] = min_d + $adj[min_u][v]
        pi[v] = min_u
      end 
    }
    q.delete min_u
  end
  # remove unreachable vertice
  s.each { |u, d| 
    s.delete if d == Float::MAX
  }
  return s, pi
end

# return a set of vertice
# reachable from u
# in a sub-graph consisting only vertice
# in set search_subgraph plus u's adjacent vertice
def get_reachable_set u, route, depth
  # the sub graph to search
  # include all vertice in route 
  # as well as adjacent vertice of the route
  local_search_subgraph = Set.new
  route.each { |s|
    $adj[s].each_index { |v|
      local_search_subgraph.add v if $adj[s][v] > -1
    }
  }
  puts "depth = #{depth}\tcomputing reachable set from #{u} in #{local_search_subgraph.inspect}" if $debug
  # run single-source shortest path search
  # from u within local_search_subgraph
  # the result is a set of vertice 
  # reachable from u
  # since all edges have positive weights,
  # we can use Dijistra's algorithm
  return Dijkstra local_search_subgraph, u, depth
end

# construct an array representing a shortest simple path
# from u to v
def construct_path predecessor_tree, u ,v
  simple_path = Array.new
  current_vertex = v
  while current_vertex != -1
    simple_path.insert 0, current_vertex
    current_vertex = predecessor_tree[current_vertex]
  end
  return simple_path
end

# continue to search along
# the give route
def search_from u, route, depth
  puts "depth = #{depth}\tsearching from #{u} ..." if $debug
  # find a valid search path
  if route.size == $num_vertice then
    puts "NEW ROUTE : depth = #{depth}\troute found : #{route.inspect}" if $debug
    e_time, path = get_expected_search_time_for_route route, depth
    puts "NEW ROUTE : depth = #{depth}\tcurrent min_e_time = #{$min_e_time} min_path = #{$min_path}" if $debug
    if e_time < $min_e_time then
      puts "NEW ROUTE : depth = #{depth}\t#{path} is a better route with expected search time #{e_time}!" if $debug
      $min_e_time = e_time
      $min_path = path
    end
    return
  end
  # for each vertex v reachable from current vertex u
  # in the sub-graph
  d, predecessor_tree = get_reachable_set u, route, depth
  puts "depth = #{depth}\treachable set from #{u} = #{d.keys.inspect} current_route = #{route.inspect}" if $debug
  r_set = d.keys
  for i in (0..(r_set.size-1))
    v = r_set[i]
    puts "depth = #{depth}\tv = #{v} route = #{route.inspect} include v ? #{route.include? v}" if $debug
    next if route.include? v # skip if v is already in the route
    puts "depth = #{depth}\tv = #{v}" if $debug
    route.push v
    $simple_paths[u] = construct_path predecessor_tree, u, v
    puts "depth = #{depth}\tcurrent route = #{route.inspect}" if $debug
    search_from v, route, depth+1 # search deeper from v
    route.pop # remove v from route since it's been explored
  end
  puts "depth = #{depth}\tfinished searching from #{u}" if $debug
end

# compute the expected time to find sophie
# given a specific route
def get_expected_search_time_for_route route, depth
  e_time = 0.0
  accumulated_length = 0
  path = Array.new
  print "NEW ROUTE : depth = #{depth}\troute = [" if $debug
  for i in (0..(route.size-2))
    # the end of a simple path is the new pivot
    simple_path = $simple_paths[route[i]]
    for j in (0..(simple_path.size-2))
      accumulated_length += $adj[simple_path[j]][simple_path[j+1]]
      print "#{simple_path[j]} " if $debug
      path.push simple_path[j]
    end
    e_time += accumulated_length * $vertex_prob_arr[simple_path.last]
  end
  print "#{route.last}] search_time = #{e_time}\n" if $debug
  path.push route.last
  return e_time, path
end

# compute the minimum expected time
# to find Sophie
def min_expected_time
  # start from vertex 0
  u = 0
  # route is an array of distinct vertice, aka pivots, starting from 0
  # in the order that each pivot vertex is first visited
  # in the search of sophie
  route = Array.new
  route.push u
  $simple_paths[u] = Array.new(1, u)
  # start the search from vertex 0
  search_from u, route, 0
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
# number of hiding places, i.e., vertice
$num_vertice = 0
# number of paths, i.e., edges
$num_edges = 0
# vertex name to id map; vertex_name => vertex_id
$vertex_id_map = Hash.new
# vertex probility array
$vertex_prob_arr = Array.new
# graph adjacency matrix
$adj = Array.new
# key is the start of a simple path
# value is an array and contains
# a sequence of simple path constituents
$simple_paths = Hash.new
# min expected search time
$min_e_time = Float::MAX
$min_path = nil

#########################
#   process input file  #
#########################
input_file = File.new(input_file_name, 'r')
is_num_vertice_line = true
is_vertex_line = false
is_num_edges_line = false
is_edge_line = false
vertex_id = -1
edge_id = -1
while line = input_file.gets
  # line of number of hiding places, i.e., vertice
  if is_num_vertice_line then
    $num_vertice = line.chomp().to_i
    puts "number of vertice : #{$num_vertice}" if $debug
    is_num_vertice_line = false
    is_vertex_line = true
    # initialize the adjacency matrix
    for i in (0..($num_vertice-1))
      $adj.push Array.new($num_vertice, -1) # -1 means unreachable
      $adj[i][i] = 0 # diagonal elements are all 0s
    end
    next
  end
  # lines of vertice
  # one vertex per line
  if is_vertex_line then
    vertex_id += 1
    tmp = line.chomp().split
    $vertex_id_map[tmp[0]] = vertex_id
    $vertex_prob_arr.push tmp[1].to_f
    if vertex_id == $num_vertice-1 then
      # vertice lines are all done
      is_vertex_line = false
      is_num_edges_line = true
      next
    end
  end
  # line of number of paths, i.e., edges
  if is_num_edges_line then
    $num_edges = line.chomp().to_i
    puts "number of edges : #{$num_edges}" if $debug
    is_num_edges_line = false
    is_edge_line = true
    next
  end
  # lines of edges
  # one edge per line
  if is_edge_line then
    edge_id += 1
    tmp = line.chomp().split(/\s+/)
    puts "edge line : #{tmp.inspect}" if $debug
    $adj[$vertex_id_map[tmp[0]]][$vertex_id_map[tmp[1]]] = tmp[2].to_i
    $adj[$vertex_id_map[tmp[1]]][$vertex_id_map[tmp[0]]] = tmp[2].to_i
    if edge_id == $num_edges-1 then
      break # all edges read
    end
  end
end

if $debug
  $vertex_id_map.each { |name, id| puts "vertex #{name} : #{id}; prob = #{$vertex_prob_arr[id]}" }
  puts "adjacency matrix"
  $adj.each { |row|
    row.each { |e| print "#{e}\t" }
    puts ""
  }
end

#########################
#       main process    #
#########################
min_expected_time
if $min_e_time < Float::MAX then
  printf "%0.02f\n", $min_e_time.to_f
else
  puts "-1.00"
end
puts "#{$min_path.inspect}" if $debug

#########################
#       verification    #
#########################