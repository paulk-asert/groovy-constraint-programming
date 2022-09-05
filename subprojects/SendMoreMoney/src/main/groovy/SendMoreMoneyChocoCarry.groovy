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
//@Grab('org.choco-solver:choco-solver:4.10.9')
import org.chocosolver.solver.Model
import static java.lang.System.currentTimeMillis

new Model("SEND+MORE=MONEY").with {
    def (S, M) = ['S', 'M'].collect { intVar(it, 1, 9) }
    def (E, N, D, O, R, Y) = ['E', 'N', 'D', 'O', 'R', 'Y'].collect { intVar(it, 0, 9) }
    def C = (0..3).collect{ intVar("C$it", 0, 9) }

    allDifferent(S, E, N, D, M, O, R, Y).post()
    C[3]              .eq(M).post()
    C[2].add(S).add(M).eq(O.add(C[3].mul(10))).post()
    C[1].add(E).add(O).eq(N.add(C[2].mul(10))).post()
    C[0].add(N).add(R).eq(E.add(C[1].mul(10))).post()
             D .add(E).eq(Y.add(C[0].mul(10))).post()

    def start = currentTimeMillis()
    println solver.findSolution()
    println "Solved in ${currentTimeMillis() - start} millis"
}
