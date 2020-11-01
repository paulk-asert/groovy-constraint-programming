/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2020 Paul King.
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

// What's the largest number of nuggets you can order at McDonalds
// that they can't deliver (that exact quantity)?
// See: https://www.ojalgo.org/2019/05/the-mcnuggets-challenge/

def limit = 100
int[] coeffs = [6, 9, 20]

for (int i : limit..1) {
    def model = new Model("McNuggets challenge")
    def packs6 = model.intVar("#6-packs", 0, limit.intdiv(6))
    def packs9 = model.intVar("#9-packs", 0, limit.intdiv(9))
    def packs20 = model.intVar("#20-packs", 0, limit.intdiv(20))
    IntVar[] all = [packs6, packs9, packs20]
    model.scalar(all, coeffs, "=", i).post()

    def found = model.solver.findSolution()
    if (found) {
        println "$i: $found"
    } else {
        println "Not possible to order $i nuggets"
        break
    }
}
