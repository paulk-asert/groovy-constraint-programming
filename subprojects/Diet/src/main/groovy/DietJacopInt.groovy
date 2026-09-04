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
import org.jacop.core.*
import org.jacop.search.*
import static org.jacop.core.IntDomain.MaxInt

val store = new Store()

// scale quantities by 10, coefficients by 10, products by 100
val bread  = new IntVar(store, 'bread',  0, MaxInt)
val milk   = new IntVar(store, 'milk',   0, 10)
val cheese = new IntVar(store, 'cheese', 0, MaxInt)
val potato = new IntVar(store, 'potato', 0, MaxInt)
val fish   = new IntVar(store, 'fish',   5, MaxInt)
val yogurt = new IntVar(store, 'yogurt', 0, MaxInt)

IntVar[] all = [bread, milk, cheese, potato, fish, yogurt]

val cost = new IntVar(store, 'Cost', 0, MaxInt)
store.impose(new LinearInt(all, [20, 35, 80, 15, 110, 10] as int[], '=', cost))

val protein = new IntVar(store, 'Protein', 0, 1000)
store.impose(new LinearInt(all, [40, 80, 70, 13, 80, 92] as int[], '=', protein))

val fat = new IntVar(store, 'Fat', 800, MaxInt)
store.impose(new LinearInt(all, [10, 50, 90, 1, 70, 10] as int[], '=', fat))

val carbs = new IntVar(store, 'Carbohydrates', 1000, MaxInt)
store.impose(new LinearInt(all, [150, 117, 4, 226, 0, 170] as int[], '=', carbs))

val calories = new IntVar(store, 'Calories', 30000, MaxInt)
store.impose(new LinearInt(all, [900, 1200, 1060, 970, 1300, 1800] as int[], '=', calories))

val search = new DepthFirstSearch<IntVar>()
val select = new SimpleSelect<IntVar>(all, null, new IndomainMin<IntVar>())

val result = search.labeling(store, select, cost)
println result
if (result) store.print()

// for a variation with Chocolate cake, see:
// http://www.hakank.org/jacop/Diet.java

/*
Solution cost is 1235
DFS1: DFS([bread = 0, milk = 0, cheese = 5, potato = 19, fish = 5, yogurt = 0], null, null, org.jacop.search.IndomainMin@663411de)
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
Fat = 819
Carbohydrates = 4314
Calories = 30230
 */