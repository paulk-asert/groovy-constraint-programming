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
package opta

import groovy.transform.ToString
import org.optaplanner.core.api.domain.solution.PlanningEntityCollectionProperty
import org.optaplanner.core.api.domain.solution.PlanningScore
import org.optaplanner.core.api.domain.solution.PlanningSolution
import org.optaplanner.core.api.domain.valuerange.CountableValueRange
import org.optaplanner.core.api.domain.valuerange.ValueRangeFactory
import org.optaplanner.core.api.domain.valuerange.ValueRangeProvider
import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore

@PlanningSolution
@ToString(includeNames = true, includePackage = false)
class DietSolution {
    @PlanningEntityCollectionProperty
    List<Food> foods

    @ValueRangeProvider(id = "amount")
    CountableValueRange<Integer> getAmount() {
        ValueRangeFactory.createIntValueRange(0, 200, 5)
    }

    @PlanningScore
    HardSoftScore score

    void display() {
        foods.eachWithIndex { f, idx ->
            var emoji = ['🍞', '🥛', '🧀', '🥔', '🐟', '🍶']
            println "${emoji[idx]} $f.name: ${f.amount / 100}"
        }
        for (name in ['fat', 'carbs', 'protein', 'calories', 'cost']) {
            var total = foods.sum{ f -> f."$name" * f.amount / 100 }
            printf "Total %s: %.2f%n", name, total
        }
        println "Score: $score"
    }
}
