def square_root(x):
    """
    compute root sqrt to non-negative numbers 
    taken from
    https://en.wikipedia.org/wiki/Methods_of_computing_square_roots
    """
    if x < 0:
        raise ValueError("compute root sqrt to non-negative numbers")
    # init values 
    c, d = 0, 1
    # compute highest power of four <= x
    while d <= x:
        d *= 4
    d //= 4
    # for dn to d0 
    while d != 0:
        if x >= (c + d): 
            x -= (c + d)
            c = (c >> 1) + d
        else:
            c >>= 1
        d >>= 2
    return c
