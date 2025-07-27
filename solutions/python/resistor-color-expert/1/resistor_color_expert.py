from decimal import Decimal
from math import log10

COLOR_VALUES = [
    'black', 'brown', 'red', 'orange', 'yellow', 'green', 'blue', 'violet', 'grey', 'white'
]
TOLERANCE_VALUES = {
    'grey': 0.05,
    'violet': 0.1,
    'blue': 0.25,
    'green': 0.5,
    'brown': 1,
    'red': 2,
    'gold': 5,
    'silver': 10,
}

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

def format_decimal(value):
    return Decimal(value).normalize()

def ohm_value(value):
    if value < 1000:
        return f"{value} ohms"
    log10_value = log10(value)
    log10_div3 = int(log10_value) // 3

    prefix = PREFIX_LIST[log10_div3]
    decimal_value = value / (10 ** (3 * log10_div3))
    decimal_str = "{:.2f}".format(decimal_value)
    while decimal_str[-1] in ("0", "."):
        decimal_str = decimal_str[:-1]
    return f"{decimal_str} {prefix}ohms"

def label(colors):
    *first_colors, last_color = colors
    value_colors = value(first_colors)
    last_code = color_code(last_color)
    resistence_value = value_colors * 10 ** last_code
    return resistence_value

def resistor_label(colors):
    if len(colors) == 1:
        return f"{color_code(colors[0])} ohms"
    *value_colors, tolerance_color = colors
    resistence_value = label(value_colors)
    resistence_text = ohm_value(resistence_value)
    tolerance_value = TOLERANCE_VALUES[tolerance_color]
    return f"{resistence_text} ±{tolerance_value}%"
