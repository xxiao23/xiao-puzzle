#!/usr/bin/ruby

require 'set'

# a element in the priority queue
class QueueElement
  attr_accessor :preference, :engineer_id
  
  def initialize(preference, engineer_id)
    @preference = preference
    @engineer_id = engineer_id
  end
end

# priority queue implementation
# using a max heap
class PriorityQueue
  attr_accessor :array
  
  def initialize()
    @array = Array.[](0)
  end
  
  def print_queue()
    @array.each_index { |i| print "e#{@array[i].engineer_id}/p#{@array[i].preference} " if i > 0}
    print "\n"
  end
  
  # insert a new element into the heap
  def insert(new_ele)
    # add the new ele from the tail
    @array[0] += 1
    n = @array[0]
    @array.push new_ele 
    # then move the new ele to its right place
    while n/2 > 0 and (@array[n/2].preference < @array[n].preference or (@array[n/2].preference == @array[n].preference and @array[n/2].engineer_id > @array[n].engineer_id))
      # swap the parent with the child
      tmp = @array[n/2]
      @array[n/2] = @array[n]
      @array[n] = tmp
      n = n/2
    end
  end
  
  # return the heap top
  def max()
    return @array[1]
  end
  
  # remove the heap top
  # max-heapify
  # and return the max
  def extract_max()
    max = @array[1]
    if max == nil
      puts "it is an empty heap" if $debug == 1
      return nil
    end
    
    # remove the last element
    last_ele = @array.pop
    puts "==> remove e#{max.engineer_id}" if $debug == 1
    # recude the size counter
    @array[0] -= 1
    if @array[0] == 0 then
      # no need to max-heapify
      puts "it is a 1-element heap" if $debug == 1
      return max
    end
    
    # max-heapify 
    @array[1] = last_ele
    n = 1
    while n <= @array[0]
      max_child = @array[n]
      next_pos = n
      # get the max among the parent and both children
      if @array[2*n] != nil and (max_child.preference < @array[2*n].preference or (max_child.preference == @array[2*n].preference and max_child.engineer_id < @array[2*n].engineer_id)) then
        max_child = @array[2*n]
        next_pos = 2*n
      end
      if @array[2*n+1] != nil and (max_child.preference < @array[2*n+1].preference or (max_child.preference == @array[2*n+1].preference and max_child.engineer_id < @array[2*n+1].engineer_id)) then
        max_child = @array[2*n+1]
        next_pos = 2*n+1
      end
      # already max-heapified
      if n == next_pos then
        break 
      end
      # swap the parent with the max child
      tmp = @array[n]
      @array[n] = @array[next_pos]
      @array[next_pos] = tmp
      # set the next position
      n = next_pos
    end
    
    return max 
  end
  
end

# compute score for a -> b
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

######################################
# main #
######################################
if (ARGV[1])
  $debug = 1
else
  $debug = 0
end

$scores = Array.new
$drink_preferences = Array.new
$preference_hash_array = Array.new
$line_num = 0
$line_engineers = 0
$line_drinks = 0
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
puts "50 percentile engineer #{$half_percentile_index}" unless $debug == 0
$paired_flag = Hash.new # 0 for not paired yet; 1 otherwise
$unpaired_set = SortedSet.new # engineers yet to be paired
$queues = Array.new # a array of priority queues, one for each engineer preferring others
$reverse_queues = Array.new # a array of reverse priority queues, one for each engineer being preferred
for i in (0..($num_engineers-1))
  $paired_flag[i] = 0 
  $unpaired_set.add(i)
  $queues.push PriorityQueue.new
  $reverse_queues.push PriorityQueue.new
end

# fill in scores table O(n^2)
# build up a forward priority queue and a reverse priority queue
# for each lower engineer
for i in (0..$num_engineers-1)
  $scores.push Array.new
  for j in (0..($num_engineers-1))
    if (i<=$half_percentile_index and j<=$half_percentile_index) or (i>$half_percentile_index and j>$half_percentile_index)
      $scores[i].push 0
    else 
      $scores[i].push get_score(i,j)
      puts "insert #e{j} into forward priority queue for e#{i}" if $debug == 1
      $queues[i].insert QueueElement.new($scores[i][j], j)
      puts "insert #e{i} into reverse priority queue for e#{j}" if $debug == 1
      $reverse_queues[j].insert(QueueElement.new($scores[i][j], i))  
    end
  end
end

puts "scores" unless $debug == 0
if $debug == 1
  print "id  "
  for i in (0..($num_engineers-1))
    print "#{i}  "
  end
  print "\n"
end
$scores.each_index { |i| 
  print "#{i}  "
  $scores[i].each { |s| print "#{s}  " }
  print "\n" 
} unless $debug == 0
print "\n" unless $debug == 0

$num_total_runs = 0
$engineer_pairs = Hash.new # lower_engineer_id => upper_engineer_id

# print the array of priority queues for debug
if $debug == 1 then
  $reverse_queues.each_index { |l| 
    puts "forward priority queue for e#{l}"
    $queues[l].print_queue
    puts "reverse priority queue for e#{l}"
    $reverse_queues[l].print_queue
  }
end

# figure out the pairings
while !$unpaired_set.empty? # there are engineers yet to be paired
  $num_total_runs += 1
  puts "*********************** TRY #{$num_total_runs} ***********************" if $debug == 1
  $unpaired_set.each { |e| print "#{e} " } if $debug == 1
  print "\n" if $debug == 1
  tmp_hash = Hash.new # key => an engineer; value => his most preferred engineer
  $reverse_queues.each_index { |i|
    next if $paired_flag[i] == 1 
    puts "looking at e#{i}" if $debug == 1
    puts "reverse priority queue for e#{i}" if $debug == 1
    $reverse_queues[i].print_queue if $debug == 1
    # most-preferred-by high engineer not paired yet
    tmp_ele = $reverse_queues[i].max
    while $paired_flag[tmp_ele.engineer_id] == 1
        $reverse_queues[i].extract_max 
        tmp_ele = $reverse_queues[i].max
        return "ERROR priority queue for e#{i} should not be empty" if tmp_ele == nil
    end   
    engineer_id = tmp_ele.engineer_id
    preference = tmp_ele.preference
    puts "top pick e#{engineer_id} for e#{i}" if $debug == 1
    if tmp_hash.key?(engineer_id) then
      # if i is more preferred than the current pick
      if $scores[engineer_id][tmp_hash[engineer_id]] < preference or ($scores[engineer_id][tmp_hash[engineer_id]] == preference and tmp_hash[engineer_id] < i) then
        tmp_hash[engineer_id] = i
      end
    else
      tmp_hash[engineer_id] = i
    end
  }
  # pair mutally preferred couples first
  num_mutually_preferred = 0
  tmp_hash.each { |h, l|
    puts "looking at tmp_hash[e#{h}] = e#{l}" if $debug == 1    
    if tmp_hash[l] == h then
      next if h >= $num_engineers/2
      $engineer_pairs[h] = l
      $engineer_pairs[l] = h
      $paired_flag[h] = $paired_flag[l] = 1
      $unpaired_set.delete(h)
      $unpaired_set.delete(l)
      puts "mutally-preferred paired e#{h} with e#{l}" if $debug == 1
      num_mutually_preferred += 1
    end
  }
  puts "\nnum mutually preferred = #{num_mutually_preferred}" if $debug == 1
  
  if num_mutually_preferred == 0 then
    tmp_engineer_pairs = Hash.new
    tmp_hash.each { |h, l|
      # now need to validate the pairing of h<->l
      found_violation = 0
      puts "***********************" if $debug == 1
      puts "examining e#{h}<->e#{l}" if $debug == 1
      puts "***********************" if $debug == 1
      # if h wants to switch paterner
      puts "assuming e#{h} wants to switch" if $debug == 1
      if h < $num_engineers/2 then
        start_index = $num_engineers/2
        end_index = $num_engineers-1
      else
        start_index = 0
        end_index = $num_engineers/2 - 1
      end
      # see if exist h->ll such that h and ll are mutually more preferred
      for ll in (start_index..end_index)
        next if ll == l
        hh0 = -1
        if $engineer_pairs.has_key?(ll)
          puts "ll = e#{ll} is alreay paired" if $debug == 1
          hh0 = $engineer_pairs[ll] if $engineer_pairs[ll] != h
        end
        hh1 = -1
        if tmp_hash.has_key?(ll)
          puts "ll = e#{ll} is in tmp_hash keys" if $debug == 1
          hh1 = tmp_hash[ll] if tmp_hash[ll] != h
        end
        hh2 = -1
        if tmp_hash.has_value?(ll)
          puts "ll = e#{ll} is in tmp_hash values" if $debug == 1
          hh2 = tmp_hash.index(ll) if tmp_hash.index(ll) != h
        end
        puts "ll = e#{ll}, hh0 = e#{hh0}, hh1 = e#{hh1}, hh2 = e#{hh2}" if $debug == 1
        [hh0, hh1, hh2].each { |hh| 
          next if hh == -1
          if ($scores[h][l] < $scores[h][ll] or ($scores[h][l] == $scores[h][ll] and l < ll)) and ($scores[ll][h] > $scores[ll][hh] or ($scores[ll][h] == $scores[ll][hh] and h > hh))
            puts "VIOLATION e#{h}->e#{l} : better choice e#{h}->e#{ll}"
            puts "scores : e#{h}->e#{l}=#{$scores[h][l]} e#{h}->e#{ll}=#{$scores[h][ll]} e#{ll}->e#{h}=#{$scores[ll][h]} e#{ll}->e#{hh}=#{$scores[ll][hh]}"
            found_violation = 1
            break;
          end
        }
      end
      next if found_violation == 1
      puts "assuming e#{l} wants to switch" if $debug == 1
      # if l wants to switch parterner
      if l < $num_engineers/2 then
        start_index = $num_engineers/2
        end_index = $num_engineers-1
      else
        start_index = 0
        end_index = $num_engineers/2 - 1
      end
      # see if exist h->ll such that h and ll are mutually more preferred
      for hh in (start_index..end_index)
        next if hh == h
        ll0 = -1
        if $engineer_pairs.has_key?(hh)
          puts "hh = e#{hh} is alreay paired" if $debug == 1
          ll0 = $engineer_pairs[hh] if $engineer_pairs[hh] != l
        end
        ll1 = -1
        if tmp_hash.has_key?(hh)
          puts "hh = e#{hh} is in tmp_hash keys" if $debug == 1
          ll1 = tmp_hash[hh] if tmp_hash[hh] != l
        end
        ll2 = -1
        if tmp_hash.has_value?(hh)
          puts "hh = e#{hh} is in tmp_hash values" if $debug == 1
          ll2 = tmp_hash.index(hh) if tmp_hash.index(hh) != l
        end
        puts "hh = e#{hh}, ll0 = e#{ll0}, ll1 = e#{ll1}, ll2 = e#{ll2}" if $debug == 1
        [ll0, ll1, ll2].each { |ll| 
          next if ll == -1
          if ($scores[l][h] < $scores[l][hh] or ($scores[l][h] == $scores[l][hh] and h < hh)) and ($scores[hh][l] > $scores[hh][ll] or ($scores[hh][l] == $scores[hh][ll] and l > ll))
            puts "VIOLATION e#{l}->e#{h} : better choice e#{l}->e#{hh}"
            puts "scores : e#{l}->e#{h}=#{$scores[l][h]} e#{l}->e#{hh}=#{$scores[l][hh]} e#{hh}->e#{l}=#{$scores[hh][l]} e#{hh}->e#{ll}=#{$scores[hh][ll]}"
            found_violation = 1
            break
          end
        }
      end
      next if found_violation == 1
      puts "--------------------" if $debug == 1
      puts "approve e#{h}->e#{l}" if $debug == 1
      puts "--------------------" if $debug == 1
      tmp_engineer_pairs[h] = l
      tmp_engineer_pairs[l] = h
      $paired_flag[h] = $paired_flag[l] = 1
      $engineer_pairs[h] = l
      $engineer_pairs[l] = h
      $unpaired_set.delete h
      $unpaired_set.delete l
    }
  end
  
  # print the array of priority queues for debug
  if $debug == 1 then
    $reverse_queues.each_index { |l| 
      puts "forward priority queue for e#{l}"
      $queues[l].print_queue
      puts "reverse priority queue for e#{l}"
      $reverse_queues[l].print_queue
    }
    puts "Paired So Far"
    $engineer_pairs.each { |u, l| puts "e#{u}->e#{l}" if u < $num_engineers/2}
  end
end

# print out the result
puts "num of total tries = #{$num_total_runs}" if $debug == 1

# initial assignment done
# do validation now
exit if $debug == 0
puts "validation!!"
found_violation = 0
begin  
  $engineer_pairs.each { |h, l|
    found_violation = 0
    puts "e#{h} -> e#{l}"
    if h >= $num_engineers/2 then
      puts "skip" if $debug == 1
      next
    end
    # see if exist h->ll
    for ll in ($num_engineers/2..$num_engineers-1)
      next if ll == l
      hh = $engineer_pairs[ll]
      if ($scores[h][l] < $scores[h][ll] or ($scores[h][l] == $scores[h][ll] and l < ll)) and ($scores[ll][h] > $scores[ll][hh] or ($scores[ll][h] == $scores[ll][hh] and h > hh))
        puts "VIOLATION e#{h}->e#{l} : better choice e#{h}->e#{ll}"
        puts "scores : e#{h}->e#{l}=#{$scores[h][l]} e#{h}->e#{ll}=#{$scores[h][ll]} e#{ll}->e#{h}=#{$scores[ll][h]} e#{ll}->e#{hh}=#{$scores[ll][hh]}"
        found_violation = 1
        # pair up h<->ll
        $engineer_pairs[h] = ll
        $engineer_pairs[ll] = h
        $engineer_pairs[hh] = l
        $engineer_pairs[l] = hh
        puts "new pairings e#{h}<->e#{ll} e#{hh}->e#{l}" if $debug == 1
        break
      end
    end
    next if found_violation == 1
    # see if exist hh->l
    for hh in (0..$num_engineers/2-1)
      next if hh == h
      ll = $engineer_pairs[hh]
      if ($scores[l][h] < $scores[l][hh] or ($scores[l][h] == $scores[l][hh] and h < hh)) and ($scores[hh][l] > $scores[hh][ll] or ($scores[hh][l] == $scores[hh][ll] and l > ll))
        puts "VIOLATION e#{h}->e#{l} : better choice e#{hh}->e#{l}"
        puts "scores : e#{l}->e#{h}=#{$scores[l][h]} e#{l}->e#{hh}=#{$scores[l][hh]} e#{hh}->e#{l}=#{$scores[hh][l]} e#{hh}->e#{ll}=#{$scores[hh][ll]}"
        found_violation = 1
        # pair up hh<->l
        $engineer_pairs[hh] = l
        $engineer_pairs[l] = hh
        $engineer_pairs[h] = ll
        $engineer_pairs[ll] = h
        puts "new pairings e#{hh}<->e#{l} e#{h}<->e#{ll}" if $debug == 1
        break
      end
    end
  }
end while (found_violation == 1)

puts "NO VIOLATIONS!" if $debug == 1
$sort_engineer_pairs = $engineer_pairs.sort_by { |k ,v| k}
$sort_engineer_pairs.each { |u, l| puts "#{u} #{l}" if u < $num_engineers/2 }