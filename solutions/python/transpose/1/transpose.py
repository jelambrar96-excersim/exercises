from itertools import zip_longest

def aux(iterable):
    out = []
    flag = True
    for item in reversed(iterable):
        if item is None and flag:
            continue
        flag = False
        if item is None:
            out.append(" ")
        else:
            out.append(item)
    return "".join(reversed(out))

def transpose(text):
    rows = text.split("\n")
    mat = [ aux(word) for word in zip_longest(*rows, fillvalue=None) ]
    return "\n".join(mat)