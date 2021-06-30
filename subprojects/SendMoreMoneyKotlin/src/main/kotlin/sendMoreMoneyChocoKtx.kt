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
 // This example uses a nice extension to Kotlin for Choco:
 // https://github.com/ideaplugins/choco-ktx
 // This extension may not be in widespread use and since the
 // bintray shutdown, isn't currently in a public repo.
import ar.com.agomez.choco.*

fun main(args: Array<String>) {
    model("send+more=money") {
        val s = digitNonZero("s")
        val e = digit("e")
        val n = digit("n")
        val d = digit("d")
        val m = digitNonZero("m")
        val o = digit("o")
        val r = digit("r")
        val y = digit("y")
        allDifferent(s, e, n, d, m, o, r, y).post()
        scalar { (1000 * s + 100 * e + 10 * n + d + 1000 * m + 100 * o + 10 * r + e - 10000 * m - 1000 * o - 100 * n - 10 * e - y) eq 0 }.post()
        solver.showSolutions()
        solver.solveAll()
    }
}
