//@Grab('org.choco-solver:choco-solver:4.10.0')
import org.chocosolver.solver.Model
import org.chocosolver.solver.variables.IntVar
import org.chocosolver.solver.variables.RealVar

def model = new Model("Diet problem")
def unbounded = 10000
def precision = 0.00001d

// scale quantities by 10, coefficients by 10, products by 100
def bread = model.realVar("Bread", 0, unbounded, precision)
def milk = model.realVar("Milk", 0, 10, precision)
def cheese = model.realVar("Cheese", 0, unbounded, precision)
def potato = model.realVar("Potato", 0, unbounded, precision)
def fish = model.realVar("Fish", 5, unbounded, precision)
def yogurt = model.realVar("Yogurt", 0, unbounded, precision)
RealVar[] all = [bread, milk, cheese, potato, fish, yogurt]

def cost = model.realVar("Cost", 0, unbounded, precision)
model.realIbexGenericConstraint("2.0*{0}+3.5*{1}+8.0*{2}+1.5*{3}+11.0*{4}+1.0*{5}={6}", [*all, cost] as RealVar[]).post();

def protein = model.intVar("Protein", 0, 10)
model.realIbexGenericConstraint("4.0*{0}+8.0*{1}+7.0*{2}+1.3*{3}+8.0*{4}+9.2*{5}={6}", [*all, protein] as RealVar[]).post();

def fat = model.intVar("Fat", 8, unbounded)
model.realIbexGenericConstraint("1.0*{0}+5.0*{1}+9.0*{2}+0.1*{3}+7.0*{4}+1.0*{5}={6}", [*all, fat] as RealVar[]).post();

def carbs = model.intVar("Carbohydrates", 10, unbounded)
model.realIbexGenericConstraint("15.0*{0}+11.7*{1}+0.4*{2}+22.6*{3}+0.0*{4}+17.0*{5}={6}", [*all, carbs] as RealVar[]).post();

def calories = model.intVar("Calories", 300, unbounded)
model.realIbexGenericConstraint("90*{0}+120*{1}+106*{2}+97*{3}+130*{4}+180*{5}={6}", [*all, calories] as RealVar[]).post();

model.setObjective(Model.MINIMIZE, cost)

model.solver.plugMonitor()
def found = model.solver.findSolution()
if (found) {
    all.each { println "$it.name: ${it.value}" }
    [carbs, fat, protein, calories, cost].each { println "$it.name: ${it.value}" }
} else {
    println "No solution"
}

// for a variation with Chocolate cake, see:
// http://www.hakank.org/choco/Diet.java
