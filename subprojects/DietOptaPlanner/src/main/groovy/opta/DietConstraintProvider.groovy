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

import org.optaplanner.core.api.score.stream.Constraint
import org.optaplanner.core.api.score.stream.ConstraintFactory
import org.optaplanner.core.api.score.stream.ConstraintProvider

import java.util.function.ToIntFunction

import static org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore.ONE_HARD
import static org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore.ONE_SOFT
import static org.optaplanner.core.api.score.stream.ConstraintCollectors.sum

class DietConstraintProvider implements ConstraintProvider {
    @Override
    Constraint[] defineConstraints(ConstraintFactory factory) {
        new Constraint[]{
                maxField(factory, 'protein', 10),
                minField(factory, 'fat', 8),
                minField(factory, 'carbs', 10),
                minField(factory, 'calories', 300),
                minFood(factory, 'Fish', 50),
                maxFood(factory, 'Milk', 100),
                minCost(factory),
        }
    }

    private static int amountOf(Food f, String name) {
        (f."$name" * f.amount).toInteger()
    }

    private static Constraint minField(ConstraintFactory factory, String fieldName, double minAmount) {
        ToIntFunction<Food> amount = f -> amountOf(f, fieldName)
        factory.from(Food).filter(f -> f.amount > 0)
                .groupBy(sum(amount))
                .filter(fs -> fs < minAmount * 100)
                .penalize("Min $fieldName", ONE_HARD)
    }

    private static Constraint maxField(ConstraintFactory factory, String fieldName, double maxAmount) {
        ToIntFunction<Food> amount = f -> amountOf(f, fieldName)
        factory.from(Food).filter(f -> f.amount > 0)
                .groupBy(sum(amount))
                .filter(fs -> fs > maxAmount * 100)
                .penalize("Max $fieldName", ONE_HARD)
    }

    private static Constraint minFood(ConstraintFactory factory, String foodName, double minAmount) {
        factory.from(Food)
                .filter(f -> f.name == foodName && f.amount < minAmount)
                .penalize("Min $foodName", ONE_HARD)
    }

    private static Constraint maxFood(ConstraintFactory factory, String foodName, double maxAmount) {
        factory.from(Food)
                .filter(f -> f.name == foodName && f.amount > maxAmount)
                .penalize("Max $foodName", ONE_HARD)
    }

    private static ToIntFunction<Food> totalCost = f -> (f.cost * f.amount).toInteger()

    private static Constraint minCost(ConstraintFactory factory) {
        factory.from(Food).filter(f -> f.amount > 0)
                .groupBy(sum(totalCost))
                .penalize('Min cost', ONE_SOFT, fs -> fs >> 2)
    }
}
