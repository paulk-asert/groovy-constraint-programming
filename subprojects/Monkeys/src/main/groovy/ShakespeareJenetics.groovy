//@Grab('io.jenetics:jenetics:7.0.0') // JDK17+
//@Grab('io.jenetics:jenetics:6.3.0') // JDK11+
//@Grab('io.jenetics:jenetics:5.2.0') // JDK8+
// inspired by https://gist.github.com/jesuino/bc789570bff8f5d3051d8487de8d0def
import io.jenetics.*
import io.jenetics.engine.*
import java.util.function.*

def SENTENCE = "To be or not to be?"
int DIMENSION = SENTENCE.size()
def POSSIBLE_CHARS = ('a'..'z') + ('A'..'Z') + " !?.".toList()
int SIZE = POSSIBLE_CHARS.size()
def r = new Random()

Supplier generateRandomChar = { -> POSSIBLE_CHARS[r.nextInt(SIZE)] as char }

Function fitness = { Genotype gt ->
    (0..<DIMENSION).inject(0) { sum, i ->
        def target = SENTENCE.charAt(i)
        def allele = gt.chromosome().get(i).allele()
        int exactBonus = allele == target ? 100 : 0
        sum + SIZE - (target - allele).abs() + exactBonus
    }
}

def gtf = Genotype.of(AnyChromosome.of(generateRandomChar, DIMENSION), new Chromosome[]{})
def engine = Engine.builder(fitness, gtf).offspringSelector(new RouletteWheelSelector()).build()
def result = engine.stream().limit(10000).collect(EvolutionResult.toBestGenotype())
println "Result: $result"
