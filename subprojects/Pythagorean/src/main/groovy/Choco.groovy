/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
//@Grab('org.choco-solver:choco-solver:4.10.5')
import org.chocosolver.solver.Model
import org.chocosolver.solver.variables.IntVar

// find pythagorean-triples for a, b, c <= 30

// imperative
for (x in 1..30)
  for (y in (x+1)..30)      // y > x to remove duplicates
    for (z in (y+1)..30)    // z > y for efficiency
      if (x*x + y*y == z*z)
        println "($x, $y, $z)"

// constraint-based
new Model('Pythagorean-triple').with {
  def x = intVar(1, 30)
  def y = intVar(1, 30)
  def z = intVar(1, 30)
  y.gt(x).post()      // y > x to remove duplicates
  z.gt(y).post()      // z > y for efficiency
  x.mul(x).add(y.mul(y)).eq(z.mul(z)).post()
  while (solver.solve()) {
    println "[$x.value, $y.value, $z.value]"
  }
}
