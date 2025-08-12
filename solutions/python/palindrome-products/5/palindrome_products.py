from itertools import product
from functools import reduce


def ispalindrome(number, base = 10):
    result, k = 0, number
    while k > 0:
        result = result * base + k % base
        k //= base
    return result == number


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
    for a in range(max_factor, min_factor - 1, -1):
        for b in range(a, min_factor - 1, -1):            
            p = a * b
            if max_product is not None:
                if max_product > p:
                    break
            if not ispalindrome(p):
                continue
            if max_product is None or p > max_product:
                max_product = p
                max_tuple_list = [(a,b)]
                break
            if p == max_product:
                max_tuple_list.append((a,b))
                break
    return max_product, max_tuple_list 


def smallest(max_factor, min_factor = 0):
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

    for a in range(min_factor, max_factor + 1):
        for b in range(a, max_factor + 1):      
    
            p = a * b
            if min_product is not None:
                if min_product < p:
                    break
            if not ispalindrome(p):
                continue
            if min_product is None or p < min_product:
                min_product = p
                min_tuple_list = [(a,b)]
                break
            if p == min_product:
                min_tuple_list.append((a,b))
                break
    return min_product, min_tuple_list