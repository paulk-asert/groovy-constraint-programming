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

The [SendMoreMoney subproject](subprojects/SendMoreMoney/)
illustrates solving a fairly simple cryptarithmetic puzzle
using naive brute force, permutations, and constraint-programming with Choco[1].

![SendMoreMoney](docs/images/SendMoreMoney.png)

Examples for this problem are also provided for
[Clojure](https://clojure.org/),
Haskell ([Frege](https://github.com/Frege/frege)),
[Java](https://www.java.com/),
JavaScript ([Nashorn](https://docs.oracle.com/javase/10/nashorn/)),
Ruby ([JRuby](https://www.jruby.org/)), 
Python ([Jython](https://www.jython.org/)),
[Kotlin](https://kotlinlang.org/),
Lua ([Luaj](https://github.com/luaj/luaj)),
Prolog ([tuprolog](http://apice.unibo.it/xwiki/bin/view/Tuprolog/)),
and [Scala](https://www.scala-lang.org/).

![Languages](docs/images/Languages.png)

Command-line arguments for Gradle to run the Java solution:
`:SendMoreMoney:run`

Command-line arguments for Gradle to see the task names for the JavaScript and various Groovy solutions:
```
:SendMoreMoney:tasks --group="Script"
```
Then pick one of those tasks to run, e.g. to run the Groovy permutations example:
```
:SendMoreMoney:runSendMoreMoneyPermutations
```

For the other languages, choose the appropriate subproject and arguments:
```
:SendMoreMoneyClojure:tasks --group="Script"
:SendMoreMoneyFrege:tasks --group="Script"
:SendMoreMoneyJRuby:tasks --group="Script"
:SendMoreMoneyJython:tasks --group="Script"
:SendMoreMoneyKotlin:run
:SendMoreMoneyLuaj:tasks --group="Script"
:SendMoreMoneyProlog:run
:SendMoreMoneyScala:run
```
Typically `run` is used if there is only one example, otherwise you will have to
pick one of the run tasks returned by the `tasks` command.

Alternatively, you can run the examples online using a Jupyter/Beakerx notebook:
[![Binder](https://mybinder.org/badge_logo.svg)](https://mybinder.org/v2/gh/paulk-asert/groovy-constraint-programming/master?filepath=subprojects%2FSendMoreMoney%2Fsrc%2Fmain%2Fnotebook%2FSendMoreMoney.ipynb)

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

The [Diet subproject](subprojects/Diet/)
illustrates solving an optimization/linear programming problem
using
Apache Commons Math[3],
Choco[1] (with and without ibex integration),
Jacop[4] (using scalar-product/weighted-sum and knapsack algorithms),
Ojalgo[2],
OptaPlanner[5],
OrTools[6],
and SAS/OR[7].
The slides also show solving this example using Excel and Google sheets.

![Diet](docs/images/Diet.png)

Command-line arguments for Gradle to see the task names for the available solutions:
```
:Diet:tasks --group="Script"
```
Then pick one of those tasks to run, e.g. to run the Apache Commons Math solution:
```
:Diet:runDietCommonsMath
```

---

Libraries used:

1. https://choco-solver.org/
2. https://www.ojalgo.org/
3. https://commons.apache.org/proper/commons-math/
4. https://github.com/radsz/jacop
5. https://www.optaplanner.org/
6. https://developers.google.com/optimization
7. https://www.sas.com/en_us/software/or.html (commercial product)

---
