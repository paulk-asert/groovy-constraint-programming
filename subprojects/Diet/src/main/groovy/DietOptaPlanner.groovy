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
//@Grab('org.optaplanner:optaplanner-core:7.46.0.Final')
import opta.DietSolution
import opta.Food
import org.optaplanner.core.api.solver.SolverFactory

def unsolved = new DietSolution(foods: [
        new Food(name: 'Bread', cost: 2.0, protein: 4.0, fat: 1.0, carbs: 15.0, calories: 90),
        new Food(name: 'Milk', cost: 3.5, protein: 8.0, fat: 5.0, carbs: 11.7, calories: 120),
        new Food(name: 'Potato', cost: 1.5, protein: 1.3, fat: 0.1, carbs: 22.6, calories: 97),
        new Food(name: 'Cheese', cost: 8.0, protein: 7.0, fat: 9.0, carbs: 0.4, calories: 106),
        new Food(name: 'Fish', cost: 11.0, protein: 8.0, fat: 7.0, carbs: 0.0, calories: 130),
        new Food(name: 'Yogurt', cost: 1.0, protein: 9.2, fat: 1.0, carbs: 17.0, calories: 180)
])

def factory = SolverFactory.createFromXmlResource("dietSolverConfig.xml")
def solver = factory.buildSolver()
def solved = solver.solve(unsolved)
println solved.pretty()
