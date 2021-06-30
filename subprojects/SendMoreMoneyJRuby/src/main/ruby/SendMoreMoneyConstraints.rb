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

puts "GEM_HOME: #{ENV['GEM_HOME']}"
puts "GEM_PATH: #{ENV['GEM_PATH']}"
puts "Ruby vers: #{RUBY_VERSION}, JRuby vers: #{JRUBY_VERSION}"

require 'csp-solver'

# Solves the send+more=money problem:
# http://en.wikipedia.org/wiki/Send%2Bmore%3Dmoney
problem = CSP::Solver::Problem.new

def digits_to_decimal(*digits)
  digits.inject{ |acc, digit| digit + acc*10 }
end

# variables
problem.vars([:s, :m], 1..10)
problem.vars([:e, :n, :d, :o, :r, :y], 0..10)
letters = [:s, :e, :n, :d, :m, :o, :r, :y]
problem.all_different(letters)

# constraints
problem.constraint(letters) { | s, e, n, d, m, o, r, y |
  (digits_to_decimal(s, e, n, d) + digits_to_decimal(m, o, r, e)) ==
    digits_to_decimal(m, o, n, e, y)
}

puts 's e n d m o r y'
problem.solve
puts letters.values.join(' ')
