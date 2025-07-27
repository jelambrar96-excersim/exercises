def append(list1, list2):
    list1.extend(list2)
    return list1


def concat(lists):
    return [item for sublist in lists for item in sublist]


def filter(function, list):
    return [ item for item in list if function(item)]


def length(list):
    return len(list)


def map(function, list):
    return [ function(item) for item in list ]


def foldl(function, list, initial):
    out = initial
    for item in list:
        out = function(out, item)
    return out


def foldr(function, list, initial):
    out = initial
    for item in reversed(list):
        out = function(out, item)
    return out


def reverse(list):
    return list[::-1]
