import math

def triplets_with_sum(N):

    solutions = []
    c = N // 2

    while True:
        c -= 1

        D = 2*N*c + c**2 - N**2
        if  D <= 0:
            break
        
        d = math.isqrt(D)
        if d * d != D:
            continue

        b = (N - c + d) // 2
        a = (N - c - d) // 2

        solutions.append([a,b,c])


    return solutions