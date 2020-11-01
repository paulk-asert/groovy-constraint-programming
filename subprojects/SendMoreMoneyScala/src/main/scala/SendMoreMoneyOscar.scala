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
// inspired from http://www.hakank.org/oscar/SendMoreMoney.scala

import oscar.cp._

object SendMoreMoneyOscar extends CPModel with App {
    val cp = CPSolver()

    val S = CPIntVar(1 to 9)
    val E = CPIntVar(0 to 9)
    val N = CPIntVar(0 to 9)
    val D = CPIntVar(0 to 9)
    val M = CPIntVar(1 to 9)
    val O = CPIntVar(0 to 9)
    val R = CPIntVar(0 to 9)
    val Y = CPIntVar(0 to 9)
    val all = Array(S,E,N,D,M,O,R,Y)

    add(          S*1000 + E*100 + N*10 + D +
                  M*1000 + O*100 + R*10 + E ===
        M*10000 + O*1000 + N*100 + E*10 + Y)
    add(allDifferent(all), Strong)
    search {
        binaryFirstFail(all)
    } onSolution {
        println(s"$S$E$N$D+$M$O$R$E=$M$O$N$E$Y")
    }
    start()
}
