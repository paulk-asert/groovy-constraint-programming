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
import org.chocosolver.solver.Model
import org.chocosolver.solver.variables.IntVar

val model = new Model("Diet problem")
val unbounded = 100000

// scale quantities by 10, coefficients by 10, products by 100
val bread = model.intVar("Bread", 0, unbounded)
val milk = model.intVar("Milk", 0, 10)
val cheese = model.intVar("Cheese", 0, unbounded)
val potato = model.intVar("Potato", 0, unbounded)
val fish = model.intVar("Fish", 5, unbounded)
val yogurt = model.intVar("Yogurt", 0, unbounded)
IntVar[] all = [bread, milk, cheese, potato, fish, yogurt]

val cost = model.intVar("Cost", 0, unbounded)
model.scalar(all, [20, 35, 80, 15, 110, 10] as int[], "=", cost).post()

val protein = model.intVar("Protein", 0, 1000)
model.scalar(all, [40, 80, 70, 13, 80, 92] as int[], "=", protein).post()

val fat = model.intVar("Fat", 800, unbounded)
model.scalar(all, [10, 50, 90, 1, 70, 10] as int[], "=", fat).post()

val carbs = model.intVar("Carbohydrates", 1000, unbounded)
model.scalar(all, [150, 117, 4, 226, 0, 170] as int[], "=", carbs).post()

val calories = model.intVar("Calories", 30000, unbounded)
model.scalar(all, [900, 1200, 1060, 970, 1300, 1800] as int[], "=", calories).post()

model.setObjective(Model.MINIMIZE, cost)

model.solver.plugMonitor()
val found = model.solver.findSolution()
if (found) {
    all.each { println "$it.name: ${it.value / 10}" }
    [carbs, fat, protein, calories, cost].each { println "$it.name: ${it.value / 100}" }
} else {
    println "No solution"
}

// for a variation with Chocolate cake, see:
// http://www.hakank.org/choco/Diet.java
