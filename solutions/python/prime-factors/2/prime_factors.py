def factors(value):

    primes = []
    number = 2
    while value > 1:
        if value % number == 0:
            primes.append(number)
            value //= number
        else:
            number = (number + 1) if number == 2 else (number + 2)
    return primes
