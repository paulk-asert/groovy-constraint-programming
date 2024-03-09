# Linear Programming

This subproject is part of a repo illustrating [constraint programming](../..).
This subproject illustrates solving an optimization/linear
programming problem using Timefold.

See also: [OptaPlanner solution](../DietOptaPlanner).
See also: [Other solutions](../Diet).

---

## The problem

![Diet](../../docs/images/Diet.png)

---

## The solutions

You have the following options to run the examples:

* [running locally](../../docs/RunningLocal.md) on the command-line or in an IDE
* [running via gitpod](../../docs/RunningGitpod.md)
* running as a Jupyter/BeakerX notebook [![Binder](https://mybinder.org/badge_logo.svg)](https://mybinder.org/v2/gh/paulk-asert/groovy-constraint-programming/master?filepath=subprojects%2FSendMoreMoney%2Fsrc%2Fmain%2Fnotebook%2FDiet.ipynb) ([more details](../../docs/RunningBeakerX.md))
* [running in the Groovy Console or Groovy Web Console](../../docs/RunningConsole.md)

---

### Execution via Gradle

Command-line arguments for Gradle to see the task names for the available solutions:
```
gradlew :DietOpta:tasks --group="Application"
```
Then pick one of those tasks to run, e.g. to run the Apache Commons Math solution:
```
gradlew :Diet:runDietOptaPlanner
```

---
