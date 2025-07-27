from itertools import chain

class StackUnderflowError(Exception):
    """Exception raised when Stack is not full.
       message: explanation of the error.
    """
    def __init__(self, message):
        self.message = message

DICT_OPERATIONS = {
    '+':    (lambda x, y: [y + x], 2),
    '-':    (lambda x, y: [y - x], 2),
    '*':    (lambda x, y: [y * x], 2),
    '/':    (lambda x, y: [y // x], 2),
    'dup':  (lambda x:    [x, x], 1),
    'drop': (lambda _:    [], 1),
    'swap': (lambda x, y: [x, y], 2),
    'over': (lambda x, y: [y, x, y], 2),
}

def isnumber(string_in):
    if string_in == '-':
        return False
    if string_in[0] in "-0123456789":
        if all( item in "0123456789" for item in string_in[1:]):
            return True
    return False


def substitute(custom, elems):
    return list(chain(*(custom[x] if x in custom else [x] for x in elems)))

def evaluate(input_data):
    stack = []
    custom_operations = {}
    for line in input_data:
        split_input = line.lower().split(" ")

        if split_input[0] == ':':
            if split_input[-1] != ';':
                raise Exception()
            op = split_input[1]
            if isnumber(op):
                raise ValueError("illegal operation")
            custom_operations[op] = substitute(custom_operations, split_input[2:-1])
            continue

        split_input = substitute(custom_operations, split_input)
        for item in split_input:
            if isnumber(item):
                stack.append(int(item))
            elif item in DICT_OPERATIONS.keys():
                op, count_parameters = DICT_OPERATIONS[item]
                if len(stack) < count_parameters:
                    raise StackUnderflowError("Insufficient number of items in stack")
                if item == "/" and stack[-1] == 0:
                    raise ZeroDivisionError("divide by zero")
                output_op = op(*(stack.pop() for x in range(count_parameters)))
                stack.extend(output_op)
            else:
                raise ValueError('undefined operation')

    return stack