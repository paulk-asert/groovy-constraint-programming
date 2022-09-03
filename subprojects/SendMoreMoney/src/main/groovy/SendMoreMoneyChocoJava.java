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

import org.chocosolver.solver.Model;
import org.chocosolver.solver.variables.IntVar;

import static java.lang.System.currentTimeMillis;

public class SendMoreMoneyChocoJava {
    public static void main(String[] args) {
        Model m = new Model("SEND+MORE=MONEY");
        IntVar S = m.intVar("S", 1, 9);
        IntVar E = m.intVar("E", 0, 9);
        IntVar N = m.intVar("N", 0, 9);
        IntVar D = m.intVar("D", 0, 9);
        IntVar M = m.intVar("M", 1, 9);
        IntVar O = m.intVar("O", 0, 9);
        IntVar R = m.intVar("R", 0, 9);
        IntVar Y = m.intVar("Y", 0, 9);
        m.allDifferent(S, E, N, D, M, O, R, Y).post();

        IntVar[] ALL = {
                  S, E, N, D,
                  M, O, R, E,
               M, O, N, E, Y };
        int[] COEFFS = {
                   1000,  100,  10,  1,
                   1000,  100,  10,  1,
          -10000, -1000, -100, -10, -1};
        m.scalar(ALL, COEFFS, "=", 0).post();

        long start = currentTimeMillis();
        System.out.println(m.getSolver().findSolution());
        System.out.printf("Solved in %d millis", currentTimeMillis() - start);
    }
}
