//@Grab('io.jenetics:jenetics:7.1.0') // JDK17+
//@Grab('io.jenetics:jenetics:6.3.0') // JDK11+
//@Grab('io.jenetics:jenetics:5.2.0') // JDK8+
// inspired by https://gist.github.com/jesuino/bc789570bff8f5d3051d8487de8d0def
import io.jenetics.*
import io.jenetics.engine.*
import io.jenetics.util.CharSeq

var SENTENCE = "To be or not to be?"
int DIMENSION = SENTENCE.size()
var POSSIBLE_CHARS = new CharSeq(('a'..'z') + ('A'..'Z') + " !?.".toList() as char[])
int SIZE = POSSIBLE_CHARS.size()

var fitness = { Genotype gt ->
    (0..<DIMENSION).inject(0) { sum, i ->
        var target = SENTENCE.charAt(i)
        var allele = gt.chromosome().get(i).allele()
        int exactBonus = allele == target ? 100 : 0
        sum + SIZE - (target - allele).abs() + exactBonus
    }
}

var gtf = Genotype.of([CharacterChromosome.of(POSSIBLE_CHARS, DIMENSION)])
var engine = Engine.builder(fitness, gtf).offspringSelector(new RouletteWheelSelector()).build()
var log = { EvolutionResult er -> if (er.generation() % 500 == 1) println er.bestPhenotype().genotype() }
var result = engine.stream().limit(10000).peek(log).collect(EvolutionResult.toBestGenotype())
println "Result: $result"
