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

//@Grab('it.unibo.alice.tuprolog:tuprolog:3.3.0')
import alice.tuprolog.*

def theory = '''
omit(H, [H|T], T).
omit(X, [H|T1], [H|T2]) :- omit(X, T1, T2).

assign_digits([], _).
assign_digits([H|T], List) :-
        omit(H, List, Remaining),
        assign_digits(T, Remaining).

solve(Line1, Line2, Line3) :-
        Vars = [S,E,N,D,R,Y],
        Digits = [2,3,4,5,6,7,8,9],
        assign_digits(Vars, Digits),
        M = 1,
        O = 0,
                  (1000*S + 100*E + 10*N + D +
                   1000*M + 100*O + 10*R + E) =:=
        (10000*M + 1000*O + 100*N + 10*E + Y),
        Line1 = [S,E,N,D], Line2 = [M,O,R,E], Line3 = [M,O,N,E,Y].
'''

def engine = new Prolog()
engine.setTheory(new Theory(theory))
def info = engine.solve('solve(SEND, MORE, MONEY).')
while (info.success) {
    println "solution: $info.solution - bindings: $info"
    if (engine.hasOpenAlternatives()) {
        info=engine.solveNext()
    } else {
        break
    }
}
