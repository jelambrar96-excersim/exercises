from math import lcm, gcd


def ismult(number, iterable):
    for item in iterable:
        if item == 0:
            continue
        if number % item == 0:
            return True
    return False


def brute_force(limit, numbers):
    min_number = min(numbers)
    sumatory = 0
    for i in range(min_number, limit):
        if ismult(i, numbers):
            sumatory += i
    return sumatory


def sum_of_multiples_one(limit, number):
    if number == 0:
        return 0
    division = (limit - 1)// number
    return number * division * (division + 1) // 2


def sum_of_multiples(limit, multiples):
    if len(multiples) == 0:
        return 0
    if len(multiples) == 1:
        number = multiples[0]
        return sum_of_multiples_one(limit, number)
    sumatory = 0
    if len(multiples) == 2 and gcd(*multiples) == 1:
        sumatory += sum_of_multiples_one(limit, multiples[0])
        sumatory += sum_of_multiples_one(limit, multiples[1])
        sumatory -= sum_of_multiples_one(limit, lcm(multiples[0] * multiples[1]))
        return sumatory
    return brute_force(limit, multiples)
    
