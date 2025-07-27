COLOR_VALUES = [
    'black', 'brown', 'red', 'orange', 'yellow', 'green', 'blue', 'violet', 'grey', 'white'
]

def color_code(color):
    return COLOR_VALUES.index(color)

def colors():
    return COLOR_VALUES

def value(colors):
    colors = colors[:2]
    codes = [color_code(item) for item in colors]
    suma = 0
    for i, item in enumerate(reversed(codes), start=0):
        suma += item * 10 ** i
    return suma