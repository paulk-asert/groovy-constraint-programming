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
import com.google.ortools.Loader
import com.google.ortools.linearsolver.MPSolver
import com.google.ortools.linearsolver.MPVariable

import static com.google.ortools.linearsolver.MPSolver.ResultStatus.OPTIMAL

Loader.loadNativeLibraries()

static addConstraint(MPSolver solver, List<MPVariable> vars, MPVariable comp, List<Double> coeffs) {
    solver.makeConstraint(0, 0).tap {constraint ->
        constraint.setCoefficient(comp, -1)
        vars.indices.each { i ->
            constraint.setCoefficient(vars[i], coeffs[i])
        }
    }
}

MPSolver.createSolver("SCIP").with {solver ->
    def bread  = makeNumVar(0, infinity(),'bread')
    def milk   = makeNumVar(0, 1, 'milk')
    def cheese = makeNumVar(0, infinity(), 'cheese')
    def potato = makeNumVar(0, infinity(), 'potato')
    def fish   = makeNumVar(0.5, infinity(), 'fish')
    def yogurt = makeNumVar(0, infinity(), 'yogurt')

    def food = [bread, milk, cheese, potato, fish, yogurt]

    def cost = makeNumVar(0, infinity(),'Cost')
    addConstraint(solver, food, cost, [2.0, 3.5, 8.0, 1.5, 11.0, 1.0])
    def protein = makeNumVar(0, 10,'Protein')
    addConstraint(solver, food, protein, [4.0, 8.0, 7.0, 1.3, 8.0, 9.2])
    def fat = makeNumVar(8, infinity(),'Fat')
    addConstraint(solver, food, fat, [1.0, 5.0, 9.0, 0.1, 7.0, 1.0])
    def carbs = makeNumVar(10, infinity(),'Carbs')
    addConstraint(solver, food, carbs, [15.0, 11.7, 0.4, 22.6, 0.0, 17.0])
    def cals = makeNumVar(300, infinity(),'Calories')
    addConstraint(solver, food, cals, [90, 120, 106, 97, 130, 180])

    def components = [protein, fat, carbs, cals]

    objective().with {objective ->
        objective.setCoefficient(cost, 1)
        objective.setMinimization()
        def result = solve()
        println result
        if (result == OPTIMAL) {
            println "Solution: " + objective.value()
            println "Foods: ${food.collect{ "${it.name()} ${it.solutionValue()}" }}"
            println "Components: ${components.collect{ "${it.name()} ${it.solutionValue()}" }}"
            println "Iterations: ${iterations()}, Wall time: ${wallTime()}ms"
        } else {
            System.err.println "The problem does not have an optimal solution!"
        }
    }
}

// see also:
// https://www.kaggle.com/nbuhagiar/diet-optimization-with-or-tools
