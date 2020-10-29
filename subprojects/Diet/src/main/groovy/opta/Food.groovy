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
