DICT_PAIRS = {
    '}': '{',
    ']': '[', 
    ')': '('
}

def is_paired(input_string):
    LIST_BRACKETS = []
    for item in input_string:
        if item in DICT_PAIRS.values():
            LIST_BRACKETS.append(item)
        if item in DICT_PAIRS.keys():
            if len(LIST_BRACKETS) == 0:
                return False
            if DICT_PAIRS[item] != LIST_BRACKETS[-1]:
                return False
            LIST_BRACKETS.pop(-1)
    return len(LIST_BRACKETS) == 0
