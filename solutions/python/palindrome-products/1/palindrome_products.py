from itertools import product
from functools import reduce


def ispalindrome(num):
    str_num = str(num)
    return str_num[::-1] == str_num


def largest(min_factor, max_factor):
    """Given a range of numbers, find the largest palindromes which
       are products of two numbers within that range.

    :param min_factor: int with a default value of 0
    :param max_factor: int
    :return: tuple of (palindrome, iterable).
             Iterable should contain both factors of the palindrome in an arbitrary order.
    """
    if min_factor > max_factor:
        raise ValueError('min must be <= max')
    max_product = None
    max_tuple_list = []
    range_numbers = range(min_factor, max_factor + 1)
    for a,b in product(range_numbers, range_numbers):
        if b < a:
            continue
        p = a * b
        if not ispalindrome(p):
            continue
        if max_product is None:
            max_product = p
            max_tuple_list = [(a,b)]
        elif p > max_product:
            max_product = p
            max_tuple_list = [(a,b)]
        elif p == max_product:
            max_tuple_list.append((a,b))
    print(max_product, max_tuple_list)
    return max_product, max_tuple_list 


def smallest(min_factor, max_factor):
    """Given a range of numbers, find the smallest palindromes which
    are products of two numbers within that range.

    :param min_factor: int with a default value of 0
    :param max_factor: int
    :return: tuple of (palindrome, iterable).
    Iterable should contain both factors of the palindrome in an arbitrary order.
    """
    if min_factor > max_factor:
        raise ValueError('min must be <= max')
    min_product = None
    min_tuple_list = []
    range_numbers = range(min_factor, max_factor + 1)
    for a,b in product(range_numbers, range_numbers):
        if b < a:
            continue
        p = a * b
        if not ispalindrome(p):
            continue
        if min_product is None:
            min_product = p
            min_tuple_list = [(a,b)]
        elif p < min_product:
            min_product = p
            min_tuple_list = [(a,b)]
        elif p == min_product:
            min_tuple_list.append((a,b))
    return min_product, min_tuple_list