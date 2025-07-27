DICT_ACTIONS = [
    "jump",
    "close your eyes",
    "double blink",
    "wink",
]


def commands(binary_str):
    binary_str = binary_str.zfill(5)
    binary_list = [  DICT_ACTIONS[i] for i,item in enumerate(binary_str[1:]) if item == '1' ]
    if binary_str[0] == '0':
        binary_list = list(reversed(binary_list))
    return binary_list
