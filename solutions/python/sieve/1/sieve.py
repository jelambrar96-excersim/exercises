def ismult(number, multiples):
    for i in multiples:
        if number % i == 0:
            return True
    return False

def primes(limit):
    if limit < 2:
        return []
    primes_list = [2]
    for i in range(3, limit + 1, 2):
        if ismult(i, primes_list):
            continue
        primes_list.append(i)
    return primes_list
