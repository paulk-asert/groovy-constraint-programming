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
def solutions() {
    // letters = [s', 'e' , 'n' , 'd' , 'm' , 'o' , 'r' , 'y']
    def all_solutions = []
    for (s in 1..9)
        for (e in 0..9)
            for (n in 0..9)
                for (d in 0..9)
                    for (m in 1..9)
                        for (o in 0..9)
                            for (r in 0..9)
                                for (y in 0..9)
                                    if ([s, e, n, d, m, o, r, y].toSet().size() == 8) {
                                        def send = 1000 * s + 100 * e + 10 * n + d
                                        def more = 1000 * m + 100 * o + 10 * r + e
                                        def money = 10000 * m + 1000 * o + 100 * n + 10 * e + y
                                        if (send + more == money) {
                                            all_solutions.add([send, more, money])
                                        }
                                    }
    return all_solutions
}

print(solutions())
