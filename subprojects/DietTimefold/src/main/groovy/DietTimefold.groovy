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

import ai.timefold.solver.core.api.solver.SolverFactory
import ai.timefold.solver.core.config.constructionheuristic.ConstructionHeuristicPhaseConfig
import ai.timefold.solver.core.config.heuristic.selector.move.composite.UnionMoveSelectorConfig
import ai.timefold.solver.core.config.heuristic.selector.move.generic.ChangeMoveSelectorConfig
import ai.timefold.solver.core.config.heuristic.selector.move.generic.SwapMoveSelectorConfig
import ai.timefold.solver.core.config.localsearch.LocalSearchPhaseConfig
import ai.timefold.solver.core.config.solver.SolverConfig
import timefold.DietConstraintProvider
import timefold.DietSolution
import timefold.Food

import java.time.Duration

import static ai.timefold.solver.core.config.constructionheuristic.ConstructionHeuristicType.FIRST_FIT
import static ai.timefold.solver.core.config.localsearch.LocalSearchType.VARIABLE_NEIGHBORHOOD_DESCENT

def unsolved = new DietSolution(foods: [
    new Food(name:  'Bread', cost:  2.0, protein: 4.0, fat: 1.0, carbs: 15.0, calories:  90),
    new Food(name:   'Milk', cost:  3.5, protein: 8.0, fat: 5.0, carbs: 11.7, calories: 120),
    new Food(name: 'Cheese', cost:  8.0, protein: 7.0, fat: 9.0, carbs:  0.4, calories: 106),
    new Food(name: 'Potato', cost:  1.5, protein: 1.3, fat: 0.1, carbs: 22.6, calories:  97),
    new Food(name:   'Fish', cost: 11.0, protein: 8.0, fat: 7.0, carbs:  0.0, calories: 130),
    new Food(name: 'Yogurt', cost:  1.0, protein: 9.2, fat: 1.0, carbs: 17.0, calories: 180)
])

def construction = new ConstructionHeuristicPhaseConfig(constructionHeuristicType: FIRST_FIT)
def moveSelector = new UnionMoveSelectorConfig([
        new ChangeMoveSelectorConfig(),
        new SwapMoveSelectorConfig()
])
def localSearch = new LocalSearchPhaseConfig(localSearchType: VARIABLE_NEIGHBORHOOD_DESCENT,
                                             moveSelectorConfig: moveSelector)
def config = new SolverConfig()
        .withSolutionClass(DietSolution)
        .withEntityClasses(Food)
        .withConstraintProviderClass(DietConstraintProvider)
        .withPhases(construction, localSearch)
        .withTerminationSpentLimit(Duration.ofSeconds(10))

def factory = SolverFactory.create(config)
def solver = factory.buildSolver()
println solver.solve(unsolved)

/*
08:04:32.865 [main] INFO  a.t.s.core.impl.solver.DefaultSolver - Solving started: time spent (31), best score (-6init/0hard/0soft), environment mode (REPRODUCIBLE), move thread count (NONE), random (JDK with seed 0).
08:04:33.057 [main] INFO  a.t.s.c.i.c.DefaultConstructionHeuristicPhase - Construction Heuristic phase (0) ended: time spent (225), best score (-1hard/-521soft), score calculation speed (1304/sec), step total (6).
08:04:42.832 [main] INFO  a.t.s.c.i.l.DefaultLocalSearchPhase - Local Search phase (1) ended: time spent (10000), best score (0hard/-308soft), score calculation speed (287094/sec), step total (13656).
08:04:42.833 [main] INFO  a.t.s.core.impl.solver.DefaultSolver - Solving ended: time spent (10000), best score (0hard/-308soft), score calculation speed (280601/sec), phase total (2), environment mode (REPRODUCIBLE), move thread count (NONE).
🍞 Bread: 0
🥛 Milk: 0
🧀 Cheese: 0.5
🥔 Potato: 1.9
🐟 Fish: 0.5
🍶 Yogurt: 0
Total fat: 8.19
Total carbs: 43.14
Total protein: 9.97
Total calories: 302.30
Total cost: 12.35
Score: 0hard/-308soft
 */