import ar.com.agomez.choco.*

fun main(args: Array<String>) {
    model("send+more=money") {
        val s = digitNonZero("s")
        val e = digit("e")
        val n = digit("n")
        val d = digit("d")
        val m = digitNonZero("m")
        val o = digit("o")
        val r = digit("r")
        val y = digit("y")
        allDifferent(s, e, n, d, m, o, r, y).post()
        scalar { (1000 * s + 100 * e + 10 * n + d + 1000 * m + 100 * o + 10 * r + e - 10000 * m - 1000 * o - 100 * n - 10 * e - y) eq 0 }.post()
        solver.showSolutions()
        solver.solveAll()
    }
}
