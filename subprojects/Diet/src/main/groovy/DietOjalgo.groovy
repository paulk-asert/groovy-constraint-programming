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
//@Grab('org.ojalgo:ojalgo:52.0.1')
import org.ojalgo.optimisation.ExpressionsBasedModel

val model = new ExpressionsBasedModel()

val bread = model.addVariable("Bread").lower(0)
val milk = model.addVariable("Milk").lower(0).upper(1)
val cheese = model.addVariable("Cheese").lower(0)
val potato = model.addVariable("Potato").lower(0)
val fish = model.addVariable("Fish").lower(0.5)
val yogurt = model.addVariable("Yogurt").lower(0)

val cost = model.addExpression("Cost")
cost.set(bread, 2.0).set(milk, 3.5).set(cheese, 8.0).set(potato, 1.5).set(fish, 11.0).set(yogurt, 1.0)

val protein = model.addExpression("Protein").upper(10)
protein.set(bread, 4.0).set(milk, 8.0).set(cheese, 7.0).set(potato, 1.3).set(fish, 8.0).set(yogurt, 9.2)

val fat = model.addExpression("Fat").lower(8)
fat.set(bread, 1.0).set(milk, 5.0).set(cheese, 9.0).set(potato, 0.1).set(fish, 7.0).set(yogurt, 1.0)

val carbs = model.addExpression("Carbohydrates").lower(10)
carbs.set(bread, 15.0).set(milk, 11.7).set(cheese, 0.4).set(potato, 22.6).set(fish, 0.0).set(yogurt, 17.0)

val calories = model.addExpression("Calories").lower(300)
calories.set(bread, 90).set(milk, 120).set(cheese, 106).set(potato, 97).set(fish, 130).set(yogurt, 180)

cost.weight(1.0)
val result = model.minimise()

println result
println model

// for a variation, see:
// https://www.ojalgo.org/2019/05/the-diet-problem/
