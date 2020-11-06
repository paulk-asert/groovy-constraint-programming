require 'colorize'

# Temporary test file for debugging gem loading

puts "-" * 20
puts "GEM_HOME: #{ENV['GEM_HOME']}"
puts "GEM_PATH: #{ENV['GEM_PATH']}"
puts "Ruby version: #{RUBY_VERSION}"
puts "JRuby version: #{JRUBY_VERSION}"
puts "Ruby platform: #{RUBY_PLATFORM}"
puts "-" * 20

puts "Roses are red".red
puts "Violets are blue".blue
puts "I can use JRuby/Gradle".green
puts "And now you can too!".yellow
