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
//@Grab('org.choco-solver:choco-solver:4.10.10')
import groovy.transform.CompileStatic
import groovy.transform.Immutable
import org.chocosolver.solver.Model
import org.chocosolver.solver.constraints.Constraint
import org.chocosolver.solver.expression.discrete.arithmetic.ArExpression
import org.chocosolver.solver.expression.discrete.relational.ReExpression
import org.chocosolver.solver.variables.IntVar
import static java.lang.System.currentTimeMillis

model("SEND+MORE=MONEY") {
    def (S, M) = ['S', 'M'].collect { intVar(it, 1..9) }
    def (E, N, D, O, R, Y) = ['E', 'N', 'D', 'O', 'R', 'Y'].collect { intVar(it, 0..9) }
    def C = intVarArray(4, 0..1)

    [allDifferent(S, E, N, D, M, O, R, Y),      //  C3 C2 C1 C0
     C[3]         .eq(M),                       //      S  E  N  D
    (C[2] + S + M).eq(O + C[3] * 10),           //      M  O  R  E
    (C[1] + E + O).eq(N + C[2] * 10),           //   -------------
    (C[0] + N + R).eq(E + C[1] * 10),           //   M  O  N  E  Y
           (D + E).eq(Y + C[0] * 10)]*.post()

    def start = currentTimeMillis()
    println solver.findSolution()
    println "Solved in ${currentTimeMillis() - start} millis"
}

def model(String s, @DelegatesTo(Model) Closure c) {
    def model = new Model("SEND+MORE=MONEY")
    c.delegate = model
    use(ChocoCategory, c)
}

@CompileStatic
@Immutable(knownImmutables=['delegate'])
class IntVarHolder {
    IntVar delegate
}

@CompileStatic
@Immutable(knownImmutables=['delegate'])
class ArExpressionHolder {
    ArExpression delegate
}

@CompileStatic
@Immutable(knownImmutables=['delegate'])
class ReExpressionHolder {
    ReExpression delegate
}

@CompileStatic
class ChocoCategory {
    static IntVarHolder[] intVarArray(Model self, int size, IntRange range) {
        def result = new IntVarHolder[size]
        for (n in 0..<size) {
            result[n] = intVar(self, 0..1)
        }
        result
    }

    static IntVarHolder intVar(Model self, String name, IntRange range) { new IntVarHolder(self.intVar(name, range.from, range.to)) }

    static IntVarHolder intVar(Model self, IntRange range) { new IntVarHolder(self.intVar(range.from, range.to)) }

    static Constraint allDifferent(Model self, IntVarHolder... vars) {
        IntVar[] delegates = new IntVar[vars.size()]
        for (n in 0..<vars.size()) delegates[n] = vars[n].delegate
        self.allDifferent(delegates)
    }

    static ArExpressionHolder plus(IntVarHolder self, IntVarHolder other) { new ArExpressionHolder(self.delegate.add(other.delegate)) }

    static ArExpressionHolder plus(IntVarHolder self, ArExpressionHolder other) { new ArExpressionHolder(self.delegate.add(other.delegate)) }

    static ArExpressionHolder plus(ArExpressionHolder self, ArExpressionHolder other) { new ArExpressionHolder(self.delegate.add(other.delegate)) }

    static ArExpressionHolder plus(ArExpressionHolder self, IntVarHolder other) { new ArExpressionHolder(self.delegate.add(other.delegate)) }

    static ArExpressionHolder multiply(IntVarHolder self, int other) { new ArExpressionHolder(self.delegate.mul(other)) }

    static ReExpressionHolder eq(ArExpressionHolder self, ArExpressionHolder other) { new ReExpressionHolder(self.delegate.eq(other.delegate)) }

    static ReExpressionHolder eq(IntVarHolder self, IntVarHolder other) { new ReExpressionHolder(self.delegate.eq(other.delegate)) }

    static void post(ReExpressionHolder self) { self.delegate.post() }
}
