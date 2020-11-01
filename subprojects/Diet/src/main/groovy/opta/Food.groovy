/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2020 Paul King.
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
import org.optaplanner.core.api.domain.entity.PlanningEntity
import org.optaplanner.core.api.domain.variable.PlanningVariable

@PlanningEntity
@ToString(includePackage = false)
class Food {
    String name
    @PlanningVariable(valueRangeProviderRefs = "amount")
    Integer amount // times 100
    double cost, protein, fat, carbs, calories
}
