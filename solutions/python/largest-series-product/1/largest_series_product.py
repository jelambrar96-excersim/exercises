from functools import reduce


def prod(iter1):
    return reduce(lambda x, y: x * y, iter1)


def largest_product(series, size):
    
    if size < 0:
        raise ValueError("span must not be negative")
    
    len_series = len(series)    
    if len_series < size:
        raise ValueError("span must be smaller than string length")
    
    if not all((item in "0123456789" for item in series)):
        raise ValueError("digits input must only contain digits")
    
    numbers = [ int(item) for item in series ]

    return max( prod(numbers[i: i + size]) for i in range(len_series - size + 1))
