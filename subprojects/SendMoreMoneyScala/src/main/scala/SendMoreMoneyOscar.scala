// inspired from http://www.hakank.org/oscar/SendMoreMoney.scala

import oscar.cp._

object SendMoreMoneyOscar extends CPModel with App {
    val cp = CPSolver()

    val S = CPIntVar(1 to 9)
    val E = CPIntVar(0 to 9)
    val N = CPIntVar(0 to 9)
    val D = CPIntVar(0 to 9)
    val M = CPIntVar(1 to 9)
    val O = CPIntVar(0 to 9)
    val R = CPIntVar(0 to 9)
    val Y = CPIntVar(0 to 9)
    val all = Array(S,E,N,D,M,O,R,Y)

    add(          S*1000 + E*100 + N*10 + D +
                  M*1000 + O*100 + R*10 + E ===
        M*10000 + O*1000 + N*100 + E*10 + Y)
    add(allDifferent(all), Strong)
    search {
        binaryFirstFail(all)
    } onSolution {
        println(s"$S$E$N$D+$M$O$R$E=$M$O$N$E$Y")
    }
    start()
}
