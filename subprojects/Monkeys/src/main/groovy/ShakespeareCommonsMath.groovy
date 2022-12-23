//@Grab('org.apache.commons:commons-math4:4.0-beta1')
// inspired by https://github.com/apache/commons-math/blob/MATH_3_X/src/userguide/java/org/apache/commons/math3/userguide/genetics/HelloWorldExample.java

import groovy.transform.*
import org.apache.commons.math4.legacy.genetics.*
import static org.apache.commons.math4.legacy.genetics.GeneticAlgorithm.randomGenerator as r

class Scenario {
    public static SENTENCE = "To be or not to be?"
    public static DIMENSION = SENTENCE.size()
    private static POSSIBLE_CHARS = ('a'..'z') + ('A'..'Z') + " !?.".toList()
    private static SIZE = POSSIBLE_CHARS.size()
    static getRandomChar() { POSSIBLE_CHARS[r.nextInt(SIZE)] as char }
}

int POPULATION_SIZE = 1000
double CROSSOVER_RATE = 0.9
double MUTATION_RATE = 0.03
double ELITISM_RATE = 0.1
int TOURNAMENT_ARITY = 2

var ga = new GeneticAlgorithm(
        new OnePointCrossover<Character>(), CROSSOVER_RATE,
        new RandomCharacterMutation(), MUTATION_RATE,
        new TournamentSelection(TOURNAMENT_ARITY))

var randomSentence = (0..<Scenario.DIMENSION).collect{ Scenario.randomChar }
var popList = (0..<POPULATION_SIZE).collect{ new StringChromosome(randomSentence) }
var initial = new ElitisticListPopulation(popList, 2 * popList.size(), ELITISM_RATE)

var stoppingCondition = new StoppingCondition() {
    int generation = 0

    boolean isSatisfied(Population population) {
        var fittestChromosome = population.fittestChromosome
        if (generation == 1 || generation % 10 == 0)
            println "Generation $generation: $fittestChromosome"
        generation++
        fittestChromosome.fitness().abs() < 0.1d
    }
}

println "Starting evolution ..."
var finalPopulation = ga.evolve(initial, stoppingCondition)
println "Generation $ga.generationsEvolved: $finalPopulation.fittestChromosome"

@InheritConstructors @AutoImplement
class StringChromosome extends AbstractListChromosome<Character> {
    double fitness() {
        (0..<Scenario.DIMENSION).inject(0) { sum, i ->
            var target = Scenario.SENTENCE.charAt(i)
            var allele = representation.get(i).charValue()
            // fitter if closer ascii "distance"
            sum - (target - allele).abs()
        }
    }

    StringChromosome newFixedLengthChromosome(List<Character> repr) {
        new StringChromosome(repr)
    }
}

class RandomCharacterMutation implements MutationPolicy {
    Chromosome mutate(Chromosome original) {
        int mutationIndex = r.nextInt(Scenario.DIMENSION)
        var mutation = original.representation.toList()
        mutation.set(mutationIndex, Scenario.randomChar)
        original.newFixedLengthChromosome(mutation)
    }
}
