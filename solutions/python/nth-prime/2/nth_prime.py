def prime(number):
    if number < 1:
        raise ValueError('there is no zeroth prime')
    if number == 1:
        return 2
    primes = [2]
    n = 3
    while len(primes) < number:
        if all(n % item for item in primes):
            primes.append(n)
            continue
        n += 2
    return primes[-1]