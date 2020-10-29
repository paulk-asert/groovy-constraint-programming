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
