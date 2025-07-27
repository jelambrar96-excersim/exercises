def square_root(number):
    x = number
    c = 0
    d = 1
    while d <= number:
        d *= 4
    d //= 4
    while d != 0:
        if x >= (c + d):
            x -= (c + d)
            c = (c >> 1) + d
        else:
            c >>= 1
        d >>= 2
    return c
