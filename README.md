# Groovy Constraint Programming

This repo contains examples from an Introduction to Constraint Programming talk:

https://speakerdeck.com/paulk/groovy-constraint-programming

You have 4 options to run the examples:

* [running locally](docs/RunningLocal.md) on the command-line or in an IDE
* [running via gitpod](docs/RunningGitpod.md)
* [running as a Jupyter/BeakerX](docs/RunningBeakerX.md) notebook
* [running in the Groovy Console or Groovy Web Console](docs/RunningConsole.md)

---

The [Pythagorean subproject](subprojects/Pythagorean/)
is an introductory example comparing an imperative style solution (using a brute-force approach), and
a constraint-programming solution (using Choco[1]).

![Pythagorean](docs/images/Pythagorean.png)

Run Choco solution via Jupyter/BeakerX:
[![Binder](https://mybinder.org/badge_logo.svg)](https://mybinder.org/v2/gh/paulk-asert/groovy-constraint-programming/master?filepath=subprojects%2FPythagorean%2Fsrc%2Fmain%2Fnotebook%2FPythagorean.ipynb)

Command-line arguments for Gradle to run the script:
`:Pythagorean:run`

---

The [McNuggets subproject](subprojects/McNuggets/)
illustrates solving a fairly simple Frobenius numbers puzzle
using Choco[1] and Ojalgo[2].

![McNuggets](docs/images/McNuggets.png)

Command-line arguments for Gradle to see the task names for the available solutions:
```
:McNuggets:tasks --group="Script"
```
Then pick one of those tasks to run, e.g. to run the Ojalgo solution:
```
:McNuggets:runOjalgo
```

---

## Cryptarithmetic puzzles

This example solves a classic cryptarithmetic puzzle using constraint programming.
The Groovy solution is contrasted with brute-force approaches and solutions in other JVM languages.

![Constraint programming](docs/images/cp_screenshot.png)

See the [SendMoreMoney subproject](subprojects/SendMoreMoney/) for all the details.

Constraint-programming libraries covered:
[Choco][]

Programming languages covered:
[Clojure][],
[Groovy][],
[Frege][],
[Java][],
[Nashorn][],
[JRuby][],
[Jython][],
[Kotlin][],
[Luaj][],
[Scala][],
[tuprolog][].

---

## Diet optimization

This example solves an optimization/linear programming problem.
Numerous technologies and approaches are used to solve the problem.

![Linear programming](docs/images/lp_screenshot.png)

See the [Diet subproject](subprojects/Diet/) for all the details.

Technologies illustrated:
[Apache Commons Math][],
[Choco][] (with and without ibex integration),
[JaCoP][] (using scalar-product/weighted-sum and knapsack algorithms),
[Ojalgo][],
[OptaPlanner][],
[OrTools][],
[SAS/OR][].

---

## Genetic algorithms

This example uses genetic algorithms to explore the
[infinite monkey theorem](https://en.wikipedia.org/wiki/Infinite_monkey_theorem).

![Chimpanzee at keyboard](docs/images/Chimpanzee.png)

See the [Monkeys subproject](subprojects/Monkeys/) for all the details.

Technologies illustrated:
[Apache Commons Math][],
[Jenetics][].
---

## Technology summary

Libraries used:

[Choco][],
[Apache Commons Math][],
[JaCoP][],
[OptaPlanner][],
[OrTools][],
[Jenetics][],
[SAS/OR][] (commercial product).

[Apache Commons Math]: https://commons.apache.org/proper/commons-math/
[Choco]: http://www.choco-solver.org/
[Ojalgo]: https://www.ojalgo.org/
[JaCoP]: https://github.com/radsz/jacop
[OptaPlanner]: https://www.optaplanner.org/
[OrTools]: https://developers.google.com/optimization "Google OR Tools"
[SAS/OR]: https://www.sas.com/en_us/software/or.html
[Jenetics]: https://jenetics.io/

[Clojure]: https://clojure.org/
[Groovy]: https://groovy-lang.org/
[Frege]: https://github.com/Frege/frege "JVM Haskell"
[Java]: https://www.java.com/
[Nashorn]: https://docs.oracle.com/javase/10/nashorn/ "JavaScript for JVM up to JDK"
[JRuby]: https://www.jruby.org/ "Ruby for the JVM"
[Jython]: https://www.jython.org/ "Python for the JVM"
[Kotlin]: https://kotlinlang.org/
[Luaj]: https://github.com/luaj/luaj "LUA for the JVM"
[Scala]: https://www.scala-lang.org/
[tuprolog]: http://apice.unibo.it/xwiki/bin/view/Tuprolog/ "A prolog for the JVM"

---
