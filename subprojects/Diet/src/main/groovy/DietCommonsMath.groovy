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
import static org.codehaus.groovy.util.StringUtil.bar // Groovy 5: ascii bar chart helper

static scalar(coeffs, rel, double value) {
    new LinearConstraint(coeffs as double[], rel, value)
}

val milk_max   = scalar([0, 1, 0, 0, 0, 0], LEQ, 1)
val fish_min   = scalar([0, 0, 0, 0, 1, 0], GEQ, 0.5)
val protein    = scalar([4.0, 8.0, 7.0, 1.3, 8.0, 9.2],     LEQ, 10)
val fat        = scalar([1.0, 5.0, 9.0, 0.1, 7.0, 1.0],     GEQ, 8)
val carbs      = scalar([15.0, 11.7, 0.4, 22.6, 0.0, 17.0], GEQ, 10)
val calories   = scalar([90, 120, 106, 97, 130, 180],       GEQ, 300)

LinearConstraintSet constraints = [milk_max, fish_min, protein, fat, carbs, calories]

val cost = new LinearObjectiveFunction([2.0, 3.5, 8.0, 1.5, 11.0, 1.0] as double[], 0)

val zeroOrMore = new NonNegativeConstraint(true)

val solution = new SimplexSolver().optimize(cost, constraints, zeroOrMore)

// BigDecimal rather than double: StringUtil.bar currently fails for Double/Float arguments
static pretty(int idx, BigDecimal d) {
    d ? [sprintf('%s %s %.2f', ['🍞', '🥛', '🧀', '🥔', '🐟', '🍶'][idx], bar(d, 0, 2, 20), d)] : []
}

if (solution != null) {
    printf "Cost: %.2f%n", solution.value
    println solution.point.indexed().collectMany(this::pretty).join('\n')
}
