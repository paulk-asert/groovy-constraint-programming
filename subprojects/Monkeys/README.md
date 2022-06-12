# Genetics Algorithms

This subproject is part of a repo illustrating [constraint programming](../..) and related technologies.
This subproject illustrates
solving the [infinite number of monkeys](https://en.wikipedia.org/wiki/Infinite_monkey_theorem) thought
experiment using Apache Commons Math and Jenetics.

---

## The problem

![Chimpanzee at keyboard](../../docs/images/Chimpanzee.png)

The [infinite monkey theorem](https://en.wikipedia.org/wiki/Infinite_monkey_theorem) states that a monkey hitting keys
at random on a typewriter keyboard for an infinite amount of time
will almost surely type any given text,
such as the complete works of William Shakespeare.

We'll make some simplifications. We'll assume that the monkeys:
* can only type the letters a-z, A-Z, space, and a few punctuation
* we are only looking for them to complete "To be or not to be?"

---

## The approach

Genetic algorithms 

A [genetic algorithm](https://en.wikipedia.org/wiki/Genetic_algorithm)
involves modeling a problem such that a process similar to natural selection can be applied.
A set of candidate solutions, called the population (or the phenotypes), evolve
through various iterations (or generations) toward better solutions.
Each candidate solution has a set of properties (its chromosomes or genotype)
which can be mutated and altered.

The evolution usually starts from a randomly generated population.
In each iteration (or generation), the fitness of every individual in the population is evaluated.
Some fit subset of the population are selected and for each selected individual, their
genome may be modified (recombined and possibly randomly mutated) to form a new generation.
The new generation becomes the input for the next iteration of the algorithm.
The algorithm typically terminates after a maximum number of generations have been produced,
or a target fitness level for the population has been reached.

A typical genetic algorithm requires:
* a genetic representation of the solution domain,
* a fitness function to evaluate the solution domain.

---

## The solutions

You have the following options to run the examples:

* [running locally](../../docs/RunningLocal.md) on the command-line or in an IDE
* [running via gitpod](../../docs/RunningGitpod.md)
* running as a Jupyter/BeakerX notebook [![Binder](https://mybinder.org/badge_logo.svg)](https://mybinder.org/v2/gh/paulk-asert/groovy-constraint-programming/master?filepath=subprojects%2FMonkeys%2Fsrc%2Fmain%2Fnotebook%2FMonkeys.ipynb) ([more details](../../docs/RunningBeakerX.md))
* [running in the Groovy Console or Groovy Web Console](../../docs/RunningConsole.md)

---

### Execution via Gradle

Command-line arguments for Gradle to see the task names for the available solutions:
```
gradlew :Monkeys:tasks --group="Script"
```
Then pick one of those tasks to run, e.g. to run the Apache Commons Math solution:
```
gradlew :Monkeys:runShakespeareCommonsMath
```

---
