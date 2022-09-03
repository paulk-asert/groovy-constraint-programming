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

import org.jacop.constraints.Alldifferent
import org.jacop.constraints.LinearInt
import org.jacop.core.IntVar
import org.jacop.core.Store
import org.jacop.search.DepthFirstSearch
import org.jacop.search.IndomainMin
import org.jacop.search.InputOrderSelect

import static java.lang.System.currentTimeMillis

def store = new Store()
def (S, M) = ['S', 'M'].collect { new IntVar(store, it, 1, 9) }
def (E, N, D, O, R, Y) = ['E', 'N', 'D', 'O', 'R', 'Y'].collect { new IntVar(store, it, 0, 9) }
var ctr = new Alldifferent(S, E, N, D, M, O, R, Y)
store.impose(ctr)

IntVar[] ALL = [
                S,   E,   N,   D,
                M,   O,   R,   E,
           M,   O,   N,   E,   Y ]
int[] COEFFS = [
             1000,  100,  10,  1,
             1000,  100,  10,  1,
    -10000, -1000, -100, -10, -1 ]
var lin = new LinearInt(ALL, COEFFS, "==", 0)
store.impose(lin)

def start = currentTimeMillis()
var label = new DepthFirstSearch()
var select = new InputOrderSelect(store, ALL, new IndomainMin())
label.labeling(store, select)
println "Solved in ${currentTimeMillis() - start} millis"
