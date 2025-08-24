def classify(num: int) -> str:
    """ A perfect num equals the sum of its positive divisors.

    :param num: int a positive integer
    :return: str the classification of the input integer
    """
    if num <= 0:
        raise ValueError("Classification is only possible for positive integers.")
    rangeStop, rangeStep = (num // 2, 1) if num % 2 == 0 else (num // 3, 2)
    sumFactors = sum(i for i in range(1, rangeStop + 1, rangeStep) if num % i == 0)
    return 'perfect' if num == sumFactors else ('abundant' if sumFactors > num else 'deficient')
