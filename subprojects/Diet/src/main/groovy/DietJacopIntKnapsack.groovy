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

//@Grab('org.jacop:jacop:4.7.0')
import org.jacop.constraints.knapsack.Knapsack
import org.jacop.core.IntVar
import org.jacop.core.Store
import org.jacop.search.DepthFirstSearch
import org.jacop.search.IndomainMin
import org.jacop.search.SimpleSelect
import static org.jacop.core.IntDomain.MaxInt

def store = new Store()

// scale quantities by 10, coefficients by 10, products by 100
def bread  = new IntVar(store, 'bread',  0, MaxInt)
def milk   = new IntVar(store, 'milk',   0, 10)
def cheese = new IntVar(store, 'cheese', 0, MaxInt)
def potato = new IntVar(store, 'potato', 0, MaxInt)
def fish   = new IntVar(store, 'fish',   5, MaxInt)
def yogurt = new IntVar(store, 'yogurt', 0, MaxInt)

IntVar[] all = [bread, milk, cheese, potato, fish, yogurt]
int[] price = [20, 35, 80, 15, 110, 10]
def cost = new IntVar(store, 'Cost', 0, MaxInt)

def protein = new IntVar(store, 'Protein', 0, 1000)
store.impose(new Knapsack([40, 80, 70, 13, 80, 92] as int[], price, all, cost, protein))

def fat = new IntVar(store, 'Fat', 800, MaxInt)
store.impose(new Knapsack([10, 50, 90, 1, 70, 10] as int[], price, all, cost, fat))

def carbs = new IntVar(store, 'Carbohydrates', 1000, MaxInt)
store.impose(new Knapsack([150, 117, 4, 226, 0, 170] as int[], price, all, cost, carbs))

def calories = new IntVar(store, 'Calories', 30000, MaxInt)
store.impose(new Knapsack([900, 1200, 1060, 970, 1300, 1800] as int[], price, all, cost, calories))

def search = new DepthFirstSearch<IntVar>()
def select = new SimpleSelect<IntVar>(all, null, new IndomainMin<IntVar>())

def result = search.labeling(store, select, cost)
println result
if (result) store.print()
