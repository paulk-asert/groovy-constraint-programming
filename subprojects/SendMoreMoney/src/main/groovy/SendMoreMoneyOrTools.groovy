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
import com.google.ortools.Loader
import com.google.ortools.constraintsolver.IntVar
import com.google.ortools.constraintsolver.Solver

Loader.loadNativeLibraries()

new Solver('Send+More=Money').with {
    val s = makeIntVar(1, 9, 's')
    val e = makeIntVar(0, 9, 'e')
    val n = makeIntVar(0, 9, 'n')
    val d = makeIntVar(0, 9, 'd')
    val m = makeIntVar(1, 9, 'm')
    val o = makeIntVar(0, 9, 'o')
    val r = makeIntVar(0, 9, 'r')
    val y = makeIntVar(0, 9, 'y')

    IntVar[] all = [s, e, n, d, m, o, r, y]
    IntVar[] scalar = [s, e, n, d, m, o, r, e, m, o, n, e, y]
    int[] coeffs = [
            1000,  100,  10,  1,  //  S E N D +
            1000,  100,  10,  1,  //  M O R E =
            -10000, -1000, -100, -10, -1   //  M O N E Y
    ]

    addConstraint(makeScalProdEquality(scalar, coeffs, 0))
    addConstraint(makeAllDifferent(all))

    val db = makePhase(all, INT_VAR_DEFAULT, INT_VALUE_DEFAULT)
    newSearch(db)
    while (nextSolution()) {
        println all.join(' ')
    }
    endSearch()

    // Statistics
    println "Solutions: ${solutions()}"
    println "Failures: ${failures()}"
    println "Branches: ${branches()}"
    println "Wall time: ${wallTime()}ms"
}
