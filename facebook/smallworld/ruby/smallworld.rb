#!/usr/bin/ruby

class Coordinates
  attr_accessor :id, :lat, :long, :distance
end

# calculate the distance between two friends
def cartesian_distance(friend, me)
  return (friend.lat-me.lat)**2 + (friend.long-me.long)**2
end

# build max heap
def build_max_heap(me_index)
  max_heap = []
  me = $friend_coordinates[me_index-1]
  for i in 1..$friend_coordinates.size
    next unless i!=me_index
    print "friend #{i}"
    current_coordinate = $friend_coordinates[i-1]
    puts " #{current_coordinate}"
    current_coordinate.distance = cartesian_distance(current_coordinate, me)
    # if heap is empty
    # do insertion
    if (max_heap.empty?)
      # compare with heap top
      puts "empty max_heap"
      max_heap.push(current_coordinate)
    elsif (max_heap.size < 3)
      if (max_heap[0].distance < current_coordinate.distance)
        max_heap.push(max_heap[0])
        max_heap[0] = current_coordinate
      else
        max_heap.push(current_coordinate)
      end
    else
      top_element = max_heap[0]
      if (top_element.distance > current_coordinate.distance)
        max_heap[0] = current_coordinate
        # re-heapify
        if (max_heap[1].distance > max_heap[2].distance)
          max_element = max_heap[1]
          if (max_heap[0].distance < max_element.distance)
            max_heap[1] = max_heap[0]
            max_heap[0] = max_element
          end
        else
          max_element = max_heap[2]
          if (max_heap[0].distance < max_element.distance)
            max_heap[2] = max_heap[0]
            max_heap[0] = max_element
          end
        end

      end
    end
    # sanity check
    # max_heap size always <=3
    puts "heap top #{max_heap[0].distance}"
    if (max_heap.size > 3)
      raise "Assertion failed ! max_heap size is #{max_heap.size}"
    end
  end

  for i in 0..$friend_coordinates.size-1
    puts "#{i+1} #{$friend_coordinates[i].distance}"
  end

  print "#{me_index} "
  if (max_heap[1].distance < max_heap[2].distance)
    puts "#{max_heap[1].id},#{max_heap[2].id},#{max_heap[0].id}"
  else
    puts "#{max_heap[2].id},#{max_heap[1].id},#{max_heap[0].id}"
  end
end
# a 3 node MAX-HEAP
$friend_coordinates = []

# process input file
begin
  file = File.new(ARGV[0], "r")
  while (line = file.gets)
    tmp = line.split(/\s+/)
    puts "#{tmp[0]}, #{tmp[1]}, #{tmp[2]}"
    new_coordinates = Coordinates.new
    new_coordinates.id = tmp[0].to_i
    new_coordinates.lat = tmp[1].to_f
    new_coordinates.long = tmp[2].to_f
    new_coordinates.distance = 0.0
    $friend_coordinates.push(new_coordinates)
  end
end

puts "#{$friend_coordinates}"

for i in 1..$friend_coordinates.size
  puts "me #{i}"
  build_max_heap(i)
end
