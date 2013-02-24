#!/usr/bin/ruby

$num_drinks = ARGV[1].to_i
$num_engineers = ARGV[0].to_i
$test_file = ARGV[2]

File.open($test_file, 'w') do |of|  
  # use "\n" for two lines of text  
  of.puts "#{$num_engineers} #{$num_drinks}"
  # print drink lines
  for i in (0..($num_drinks-1))
    of.puts "#{i}\tDrink #{i}"
  end
  # print engineer drink preferences
  for e in (0..($num_engineers-1))
    of.print "#{e}\t"
    num_drinks = 0
    begin
      num_drinks = rand($num_drinks+1)
    end while (num_drinks == 0) # cannot be zero
    for i in (0..(num_drinks-2)) 
      drink_id = rand($num_drinks)
      of.print "#{drink_id},"
    end
    drink_id = rand($num_drinks)
    of.print "#{drink_id}"
    of.print "\n"
  end
end
