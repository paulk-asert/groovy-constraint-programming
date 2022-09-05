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
import org.chocosolver.solver.Model
import org.chocosolver.solver.variables.IntVar

new Model("ABCD*4=DCBA").with {
    def (A, D) = ['A', 'D'].collect { intVar(it, 1, 9) }
    def (B, C) = ['B', 'C'].collect { intVar(it, 0, 9) }
    def R = (0..2).collect { intVar(0, 9) }

    allDifferent(A, B, C, D).post()
    R[2].add(A.mul(4)).eq(D).post()
    R[1].add(B.mul(4)).eq(C.add(R[2].mul(10))).post()
    R[0].add(C.mul(4)).eq(B.add(R[1].mul(10))).post()
    D.mul(4).eq(A.add(R[0].mul(10))).post()
    solver.findAllSolutions().each {
        println "$name: ${pretty(it, [A, B, C, D, ' * 4 = ', D, C, B, A])}\n$it\n"
    }
}

new Model("AA+BB+CC=ABC").with {
    def (A, B, C) = ['A', 'B', 'C'].collect { intVar(it, 1, 9) }
    allDifferent(A, B, C).post()
    A.mul(11).add(B.mul(11).add(C.mul(11))).eq(A.mul(100).add(B.mul(10)).add(C)).post()
    solver.findAllSolutions().each {
        println "$name: ${pretty(it, [A, A, ' + ', B, B, ' + ', C, C, ' = ', A, B, C])}\n$it\n"
    }
}

new Model("HALF+HALF=WHOLE").with {
    def (H, W) = ['H', 'W'].collect { intVar(it, 1, 9) }
    def (A, E, F, L, O) = ['A', 'E', 'F', 'L', 'O'].collect { intVar(it, 0, 9) }
    allDifferent(H, W, A, E, F, L, O).post()
    IntVar[] ALL = [
            H, A, L, F,
            W, H, O, L, E]
    int[] COEFFS = [
            2000, 200, 20, 2,
            -10000, -1000, -100, -10, -1]
    scalar(ALL, COEFFS, "=", 0).post()
    solver.findAllSolutions().each {
        println "$name: ${pretty(it, [H, A, L, F, ' + ', H, A, L, F, ' = ', W, H, O, L, E])}\n$it\n"
    }
}

new Model("HALF+FIFTH+TENTH+TENTH+TENTH=WHOLE").with {
    def (H, F, T, W) = ['H', 'F', 'T', 'W'].collect { intVar(it, 1, 9) }
    def (A, L, I, E, N, O) = ['A', 'L', 'I', 'E', 'N', 'O'].collect { intVar(it, 0, 9) }
    allDifferent(H, F, T, W, A, L, I, E, N, O).post()
    IntVar[] ALL = [
            H, A, L, F,
            F, I, F, T, H,
            T, E, N, T, H,
            T, E, N, T, H,
            T, E, N, T, H,
            W, H, O, L, E]
    int[] COEFFS = [
            1000, 100, 10, 1,
            10000, 1000, 100, 10, 1,
            10000, 1000, 100, 10, 1,
            10000, 1000, 100, 10, 1,
            10000, 1000, 100, 10, 1,
            -10000, -1000, -100, -10, -1]
    scalar(ALL, COEFFS, "=", 0).post()
    solver.findAllSolutions().each {
        def parts = [H, A, L, F, '+', F, I, F, T, H, '+', T, E, N, T, H, '+',
                     T, E, N, T, H, '+', T, E, N, T, H, '=', W, H, O, L, E]
        println "$name: ${pretty(it, parts)}\n$it\n"
    }
}

// helper method to print solutions
def pretty(model, parts) {
    parts.collect { p -> p instanceof IntVar ? model.getIntVal(p) : p }.join()
}
