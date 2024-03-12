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

import org.apache.commons.math4.legacy.optim.linear.*

import static org.apache.commons.math4.legacy.optim.linear.Relationship.*

static scalar(coeffs, rel, val) {
    new LinearConstraint(coeffs as double[], rel, val)
}

var bread_min  = scalar([1, 0, 0, 0, 0, 0], GEQ, 0)
var milk_min   = scalar([0, 1, 0, 0, 0, 0], GEQ, 0)
var milk_max   = scalar([0, 1, 0, 0, 0, 0], LEQ, 1)
var cheese_min = scalar([0, 0, 1, 0, 0, 0], GEQ, 0)
var potato_min = scalar([0, 0, 0, 1, 0, 0], GEQ, 0)
var fish_min   = scalar([0, 0, 0, 0, 1, 0], GEQ, 0.5)
var yogurt_min = scalar([0, 0, 0, 0, 0, 1], GEQ, 0)
var protein    = scalar([4.0, 8.0, 7.0, 1.3, 8.0, 9.2],     LEQ, 10)
var fat        = scalar([1.0, 5.0, 9.0, 0.1, 7.0, 1.0],     GEQ, 8)
var carbs      = scalar([15.0, 11.7, 0.4, 22.6, 0.0, 17.0], GEQ, 10)
var calories   = scalar([90, 120, 106, 97, 130, 180],       GEQ, 300)

LinearConstraintSet constraints = [
        bread_min, milk_min, milk_max, fish_min, cheese_min,
        potato_min, yogurt_min, protein, fat, carbs, calories
]

var cost = new LinearObjectiveFunction([2.0, 3.5, 8.0, 1.5, 11.0, 1.0] as double[], 0)

var solution = new SimplexSolver().optimize(cost, constraints)

static pretty(double d) { sprintf '%.2f', d }

if (solution != null) {
    println "Opt: ${pretty(solution.value)}"
    println solution.point.collect(this::pretty).join(', ')
}
