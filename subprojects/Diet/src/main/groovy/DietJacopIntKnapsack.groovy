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

//@Grab('org.jacop:jacop:4.8.0')
import org.jacop.constraints.LinearInt
import org.jacop.constraints.knapsack.Knapsack
import org.jacop.core.IntVar
import org.jacop.core.Store
import org.jacop.search.DepthFirstSearch
import org.jacop.search.IndomainMin
import org.jacop.search.SimpleSelect

def store = new Store()

def unbounded = 100000
// scale quantities by 10, coefficients by 10, products by 100
def bread  = new IntVar(store, 'bread',  0, unbounded)
def milk   = new IntVar(store, 'milk',   0, 10)
def cheese = new IntVar(store, 'cheese', 0, unbounded)
def potato = new IntVar(store, 'potato', 0, unbounded)
def fish   = new IntVar(store, 'fish',   5, unbounded)
def yogurt = new IntVar(store, 'yogurt', 0, unbounded)

IntVar[] all = [bread, milk, cheese, potato, fish, yogurt]
int[] price = [20, 35, 80, 15, 110, 10]
def cost = new IntVar(store, 'Cost', 0, unbounded)

def protein = new IntVar(store, 'Protein', 0, 1000)
store.impose(new Knapsack([40, 80, 70, 13, 80, 92] as int[], price, all, cost, protein))

def fat = new IntVar(store, 'Fat', 800, unbounded)
store.impose(new Knapsack([10, 50, 90, 1, 70, 10] as int[], price, all, cost, fat))

def carbs = new IntVar(store, 'Carbohydrates', 1000, unbounded)
// carbs variable can lead to -ve profit which violates knapsack
//store.impose(new Knapsack([150, 117, 4, 226, 0, 170] as int[], price, all, cost, carbs))
store.impose(new LinearInt(all, [150, 117, 4, 226, 0, 170] as int[], '=', carbs))

def calories = new IntVar(store, 'Calories', 30000, unbounded)
store.impose(new Knapsack([900, 1200, 1060, 970, 1300, 1800] as int[], price, all, cost, calories))

def search = new DepthFirstSearch<IntVar>()
def select = new SimpleSelect<IntVar>(all, null, new IndomainMin<IntVar>())

def result = search.labeling(store, select, cost)
println result
if (result) store.print()

/*
Solution cost is 1235
DFS1: DFS([bread = 0, milk = 0, cheese = 5, potato = 19, fish = 5, yogurt = 0], null, null, org.jacop.search.IndomainMin@5a9d6f02)
true

*** Store
bread = 0
milk = 0
cheese = 5
potato = 19
fish = 5
yogurt = 0
Cost = 1235
Protein = 997
_1 = 0
Fat = 819
_2 = 0
Carbohydrates = 4314
Calories = 30230
_3 = 0
*** Constraint:
LinearInt1 : LinearInt( [ bread = 0, milk = 0, cheese = 5, potato = 19, yogurt = 0, Carbohydrates = 4314], [150, 117, 4, 226, 170, -1], ==, 0 )
*** Constraint:
Knapsack[ [item[ fdv: yogurt = 0, weight: 10, profit: 1800, efficiency: 180.0 ],
item[ fdv: potato = 19, weight: 15, profit: 970, efficiency: 64.66666666666667 ],
item[ fdv: bread = 0, weight: 20, profit: 900, efficiency: 45.0 ],
item[ fdv: milk = 0, weight: 35, profit: 1200, efficiency: 34.285714285714285 ],
item[ fdv: cheese = 5, weight: 80, profit: 1060, efficiency: 13.25 ],
item[ fdv: fish = 5, weight: 110, profit: 1300, efficiency: 11.818181818181818 ]],
Capacity: Cost = 1235,
Profit: Calories = 30230]

*** Constraint:
Knapsack[ [item[ fdv: milk = 0, weight: 35, profit: 50, efficiency: 1.4285714285714286 ],
item[ fdv: cheese = 5, weight: 80, profit: 90, efficiency: 1.125 ],
item[ fdv: yogurt = 0, weight: 10, profit: 10, efficiency: 1.0 ],
item[ fdv: fish = 5, weight: 110, profit: 70, efficiency: 0.6363636363636364 ],
item[ fdv: bread = 0, weight: 20, profit: 10, efficiency: 0.5 ],
item[ fdv: potato = 19, weight: 15, profit: 1, efficiency: 0.06666666666666667 ]],
Capacity: Cost = 1235,
Profit: Fat = 819]

*** Constraint:
Knapsack[ [item[ fdv: yogurt = 0, weight: 10, profit: 92, efficiency: 9.2 ],
item[ fdv: milk = 0, weight: 35, profit: 80, efficiency: 2.2857142857142856 ],
item[ fdv: bread = 0, weight: 20, profit: 40, efficiency: 2.0 ],
item[ fdv: cheese = 5, weight: 80, profit: 70, efficiency: 0.875 ],
item[ fdv: potato = 19, weight: 15, profit: 13, efficiency: 0.8666666666666667 ],
item[ fdv: fish = 5, weight: 110, profit: 80, efficiency: 0.7272727272727273 ]],
Capacity: Cost = 1235,
Profit: Protein = 997]
 */