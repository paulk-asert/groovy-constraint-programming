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
package timefold

import ai.timefold.solver.core.api.domain.solution.PlanningEntityCollectionProperty
import ai.timefold.solver.core.api.domain.solution.PlanningScore
import ai.timefold.solver.core.api.domain.solution.PlanningSolution
import ai.timefold.solver.core.api.domain.valuerange.ValueRange
import ai.timefold.solver.core.api.domain.valuerange.ValueRangeFactory
import ai.timefold.solver.core.api.domain.valuerange.ValueRangeProvider
import ai.timefold.solver.core.api.score.HardSoftScore

@PlanningSolution
class DietSolution {
    @PlanningEntityCollectionProperty
    List<Food> foods

    @ValueRangeProvider(id = "amount")
    ValueRange<Integer> getAmount() {
        ValueRangeFactory.createIntValueRange(0, 200, 5)
    }

    @PlanningScore
    HardSoftScore score

    String toString() {
        val sb = new StringBuilder()
        val emoji = ['🍞', '🥛', '🧀', '🥔', '🐟', '🍶']
        for (int idx, var f in foods) { // Groovy 5: index variable in for loops
            sb << "${emoji[idx]} $f.name: ${f.amount / 100}\n"
        }
        for (name in ['fat', 'carbs', 'protein', 'calories', 'cost']) {
            val total = foods.sum{ f -> f."$name" * f.amount / 100 }
            sb << sprintf("Total %s: %.2f%n", name, total)
        }
        sb << "Score: $score"
        sb
    }
}
