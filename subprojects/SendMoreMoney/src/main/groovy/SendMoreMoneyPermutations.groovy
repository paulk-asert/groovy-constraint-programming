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
def digits = 0..9
long start = System.currentTimeMillis()
for (p in digits.permutations()) {
    if (p[-1] < p[-2]) continue
    def (s, e, n, d, m, o, r, y) = p
    if (s == 0 || m == 0) continue
    def send = 1000 * s + 100 * e + 10 * n + d
    def more = 1000 * m + 100 * o + 10 * r + e
    def money = 10000 * m + 1000 * o + 100 * n + 10 * e + y
    if (send + more == money) {
        println "s = $s, e = $e, n = $n, d = $d"
        println "m = $m, o = $o, r = $r, y = $y"
    }
}
println System.currentTimeMillis() - start
