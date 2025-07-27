def egg_count(display_value):
    n = 0
    while display_value > 0:
        n += (display_value % 2)
        display_value //= 2
    return n
