#  * SPDX-License-Identifier: Apache-2.0
#
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
#     https://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.

require 'set'
require 'java'

jv = java.lang.System.get_property 'java.version'

def digits_to_decimal(*digits)
  digits.inject{ |acc, digit| digit + acc*10 }
end

puts "Java vers: #{jv}, Ruby vers: #{RUBY_VERSION}, JRuby vers: #{JRUBY_VERSION}"
#puts "Ruby platform: #{RUBY_PLATFORM}"
puts "GEM_HOME: #{ENV['GEM_HOME']}"
#puts "os.name #{java.lang.System.get_property 'os.name'}"
#puts "user.dir #{java.lang.System.get_property 'user.dir'}"
#puts "gem.home #{java.lang.System.get_property 'gem.home'}"

m = 1
for s in 1..9 do
  for e in 0..9 do
    for n in 0..9 do
      for d in 0..9 do
        for o in 0..9 do
          for r in 0..9 do
            for y in 0..9 do
              if (Set.new([s, e, n, d, m, o, r, y]).size() == 8 &&
                  digits_to_decimal(s, e, n, d) +
                  digits_to_decimal(m, o, r, e) ==
                  digits_to_decimal(m, o, n, e, y))
                puts "#{[s, e, n, d]}"
                puts "#{[m, o, r, e]}"
                puts "#{[m, o, n, e, y]}"
              end
            end
          end
        end
      end
    end
  end
end
