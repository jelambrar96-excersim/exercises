def isDivisible(number, list):
    for item in list:
        if number % item == 0:
            return True
    return False


def prime(number):
    if number < 1:
        raise ValueError('there is no zeroth prime')
    if number == 1:
        return 2
    primes = [2]
    current_prime = 3
    index_prime = 1
    while index_prime < number:
        if isDivisible(current_prime, primes):
            current_prime += 2
            continue
        primes.append(current_prime)
        index_prime += 1
    return primes[-1]