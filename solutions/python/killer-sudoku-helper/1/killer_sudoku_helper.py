from itertools import combinations as combs


def combinations(target, size, exclude):
    numbers_in = set(range(1,10)) - set(exclude)
    return [ list(item) for item in combs(numbers_in, size) if sum(item) == target]
