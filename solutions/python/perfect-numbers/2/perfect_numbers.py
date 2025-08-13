def classify(number: int) -> str:
    """ A perfect number equals the sum of its positive divisors.

    :param number: int a positive integer
    :return: str the classification of the input integer
    """
    if number <= 0:
        raise ValueError("Classification is only possible for positive integers.")
    sum = 0
    rangeStop, rangeStep = (number // 2, 1) if number % 2 == 0 else (number // 3, 2)
    for i in range(1, rangeStop + 1, rangeStep):
        if number % i == 0:
            sum += i
    if number == sum:
        return 'perfect'
    if number < sum:
        return 'abundant'
    return 'deficient'
