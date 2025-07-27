def factors(value):

    primes = []
    number = 2
    while value > 1:
        if value % number == 0:
            primes.append(number)
            value //= number
        else:
            number += 1
    return primes
