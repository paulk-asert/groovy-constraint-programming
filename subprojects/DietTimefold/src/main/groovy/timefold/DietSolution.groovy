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

import groovy.transform.ToString
import ai.timefold.solver.core.api.domain.solution.PlanningEntityCollectionProperty
import ai.timefold.solver.core.api.domain.solution.PlanningScore
import ai.timefold.solver.core.api.domain.solution.PlanningSolution
import ai.timefold.solver.core.api.domain.valuerange.CountableValueRange
import ai.timefold.solver.core.api.domain.valuerange.ValueRangeFactory
import ai.timefold.solver.core.api.domain.valuerange.ValueRangeProvider
import ai.timefold.solver.core.api.score.buildin.hardsoft.HardSoftScore

@PlanningSolution
@ToString(includeNames = true, includePackage = false)
class DietSolution {
    @PlanningEntityCollectionProperty
    List<Food> foods

    @ValueRangeProvider(id = "amount")
    CountableValueRange<Integer> getAmount() {
        ValueRangeFactory.createIntValueRange(0, 200)
    }

    @PlanningScore
    HardSoftScore score

    String pretty() {
        def sb = new StringBuilder()
        foods.each {
            sb << "$it.name: ${it.amount / 100}\n"
        }
        for (name in ['fat', 'carbs', 'protein', 'calories', 'cost']) {
            sb << "Total $name: ${foods.sum{ f -> f."$name" * f.amount / 100 }}\n"
        }
        sb << "Score: $score"
        sb
    }
}
