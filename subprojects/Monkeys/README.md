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
