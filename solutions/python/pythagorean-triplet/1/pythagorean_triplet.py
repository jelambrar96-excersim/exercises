import math

def triplets_with_sum(number):
    solutions = []
    for c in range(number//3, number // 2):
        for a in range(1, c):
            b = number - c - a
            if b <= a:
                break
            if (a**2 + b**2) == c**2:
                solutions.append([a,b,c])

    return solutions