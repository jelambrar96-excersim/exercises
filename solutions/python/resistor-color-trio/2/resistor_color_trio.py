from math import log10, floor

COLOR_VALUES = [
    'black', 'brown', 'red', 'orange', 'yellow', 'green', 'blue', 'violet', 'grey', 'white'
]

def color_code(color):
    return COLOR_VALUES.index(color)

def colors():
    return COLOR_VALUES

def value(colors):
    codes = [color_code(item) for item in colors]
    suma = 0
    for i, item in enumerate(reversed(codes), start=0):
        suma += item * 10 ** i
    return suma

PREFIX_LIST = ["", "kilo", "mega", "giga"]

def ohm_value(value):
    if value == 0:
        return "0 ohms"
    log10value = log10(value)
    log10div3 = floor(log10value) // 3
    new_value = value // (10 ** (3 * log10div3))
    return f"{new_value} {PREFIX_LIST[log10div3]}ohms"

def label(colors):
    *first_colors, last_color = colors[:3]
    value_colors = value(first_colors)
    last_code = color_code(last_color)
    resistence_value = value_colors * 10 ** last_code
    return ohm_value(resistence_value)
