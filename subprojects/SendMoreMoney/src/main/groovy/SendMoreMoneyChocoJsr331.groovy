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

import javax.constraints.*

ProblemFactory.newProblem('SEND+MORE=MONEY').with {
    def (S, M) = ['S', 'M'].collect { variable(it, 1, 9) }
    def (E, N, D, O, R, Y) = ['E', 'N', 'D', 'O', 'R', 'Y'].collect { variable(it, 0, 9) }

    postAllDifferent(S, E, N, D, M, O, R, Y)

    Var[] ALL = [
            S, E, N, D,
            M, O, R, E,
            M, O, N, E, Y]
    int[] COEFFS = [
            1000, 100, 10, 1,
            1000, 100, 10, 1,
            -10000, -1000, -100, -10, -1]

    post(COEFFS, ALL, '=', 0)

    def solver = getSolver()
    def solution = solver.findSolution()
    println solution ?: 'No solution'
    solver.logStats()
}
