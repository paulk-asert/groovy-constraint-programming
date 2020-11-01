from constraint import *

def solve():
    problem = Problem()

    problem.addVariables("sm", range(1,10))
    problem.addVariables("endory", range(10))
    # alternative to above two clauses
    # problem.addVariables("sendmory", range(10))
    # problem.addConstraint(NotInSetConstraint([0]), "sm")

    # not needed but makes the problem more efficient (10.5s vs 3.5s)
    # problem.addConstraint(lambda d, e, y: (d + e) % 10 == y, "dey")

    problem.addConstraint(
        lambda s, e, n, d, m, o, r, y: 1000 * s +
                                       100 * e +
                                       10 * n +
                                       d +
                                       1000 * m +
                                       100 * o +
                                       10 * r +
                                       e ==
                                       10000 * m + 1000 * o + 100 * n + 10 * e + y,
        "sendmory",
    )
    problem.addConstraint(AllDifferentConstraint())
    solutions = problem.getSolutions()
    return solutions


def main():
    solutions = solve()
    print("SEND+MORE=MONEY")
    for s in solutions:
        print(
            "%(s)d%(e)d%(n)d%(d)d+"
            "%(m)d%(o)d%(r)d%(e)d="
            "%(m)d%(o)d%(n)d%(e)d%(y)d" % s
        )


if __name__ == "__main__":
    main()