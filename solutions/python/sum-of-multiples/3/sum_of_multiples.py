def sum_of_multiples(limit, multiples):
    """
    Calculate the sum of all unique multiples of given factors below a specified limit.

    :param limit: The upper limit (exclusive) for the multiples.
    :param multiples: A list of factors to consider for finding multiples.
    :return: The sum of all unique multiples of the factors below the limit.
    """
    if 0 in multiples:
        multiples.remove(0)
    return sum({
            i
            for mult in multiples
            for i in range(mult, limit, mult)
        })
