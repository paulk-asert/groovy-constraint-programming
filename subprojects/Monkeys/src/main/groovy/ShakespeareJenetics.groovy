//@Grab('io.jenetics:jenetics:7.1.0') // JDK17+
//@Grab('io.jenetics:jenetics:6.3.0') // JDK11+
//@Grab('io.jenetics:jenetics:5.2.0') // JDK8+
// inspired by https://gist.github.com/jesuino/bc789570bff8f5d3051d8487de8d0def
import io.jenetics.*
import io.jenetics.engine.*
import io.jenetics.util.CharSeq

def SENTENCE = "To be or not to be?"
int DIMENSION = SENTENCE.size()
def POSSIBLE_CHARS = new CharSeq(('a'..'z') + ('A'..'Z') + " !?.".toList() as char[])
int SIZE = POSSIBLE_CHARS.size()

def fitness = { Genotype gt ->
    (0..<DIMENSION).inject(0) { sum, i ->
        def target = SENTENCE.charAt(i)
        def allele = gt.chromosome().get(i).allele()
        int exactBonus = allele == target ? 100 : 0
        sum + SIZE - (target - allele).abs() + exactBonus
    }
}

def gtf = Genotype.of([CharacterChromosome.of(POSSIBLE_CHARS, DIMENSION)])
def engine = Engine.builder(fitness, gtf).offspringSelector(new RouletteWheelSelector()).build()
def log = { EvolutionResult er -> if (er.generation() % 500 == 1) println er.bestPhenotype().genotype() }
def result = engine.stream().limit(10000).peek(log).collect(EvolutionResult.toBestGenotype())
println "Result: $result"
