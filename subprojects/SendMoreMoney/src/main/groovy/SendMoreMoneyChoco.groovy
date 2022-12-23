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
//@Grab('org.choco-solver:choco-solver:4.10.10')
import org.chocosolver.solver.Model
import org.chocosolver.solver.variables.IntVar
import static java.lang.System.currentTimeMillis

new Model("SEND+MORE=MONEY").with {
    def (S, M) = ['S', 'M'].collect { intVar(it, 1, 9) }
    def (E, N, D, O, R, Y) = ['E', 'N', 'D', 'O', 'R', 'Y'].collect { intVar(it, 0, 9) }

    allDifferent(S, E, N, D, M, O, R, Y).post()

// the next line can be commented out to reduce search space but offers no improvement
//    mod(D.add(E).intVar(), 10, Y).post()

    IntVar[] ALL = [
            S, E, N, D,
            M, O, R, E,
         M, O, N, E, Y ]
    int[] COEFFS = [
            1000,  100,  10,  1,
            1000,  100,  10,  1,
   -10000, -1000, -100, -10, -1 ]

    scalar(ALL, COEFFS, "=", 0).post()

    def start = currentTimeMillis()
    println solver.findSolution()
    println "Solved in ${currentTimeMillis() - start} millis"
}
