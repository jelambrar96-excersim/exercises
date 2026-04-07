def classify(number: int) -> str:
    """ A perfect number equals the sum of its positive divisors.

    :param number: int a positive integer
    :return: str the classification of the input integer
    """
    if number <= 0:
        raise ValueError("Classification is only possible for positive integers.")
    rangeStop, rangeStep = (number // 2, 1) if number % 2 == 0 else (number // 3, 2)
    sumFactors = sum(i for i in range(1, rangeStop + 1, rangeStep) if number % i == 0)
    if number == sumFactors:
        return 'perfect'
    if number < sumFactors:
        return 'abundant'
    return 'deficient'
