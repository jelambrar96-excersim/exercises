from itertools import islice
from string import ascii_lowercase


m = len(ascii_lowercase)


def batched(iterable, n, *, strict=False):
    # batched('ABCDEFG', 3) → ABC DEF G
    if n < 1:
        raise ValueError('n must be at least one')
    iterator = iter(iterable)
    while batch := tuple(islice(iterator, n)):
        if strict and len(batch) != n:
            raise ValueError('batched(): incomplete batch')
        yield batch


def coprimes(a,b):
    mi, ma = min(a,b), max(a,b)
    for i in range(2, mi + 1):
        if mi % i == 0 and ma % i == 0:
            return True
    return False


def encode(plain_text, a, b):
    if coprimes(a,m):
        raise ValueError("a and m must be coprime.")
    list_org = [ item for item in plain_text.lower() if item in ascii_lowercase or item in "0123456789"]
    list_char  = [ item if item in "0123456789" else chr(((a * (ord(item) - 97) + b) % m) + 97) for item in list_org ]
    return " ".join([ "".join(item) for item in batched(list_char, 5)])


def decode_item(number, a, b):
    number -= b
    while True:
        if number >= 0 and number % a == 0:
            return (number // a) % m
        number += m


def decode(ciphered_text, a, b):
    if coprimes(a,m):
        raise ValueError("a and m must be coprime.")
    ciphered_text = ciphered_text.replace(" ", "")
    list_char  = [
        item if item in "0123456789" else chr(decode_item(ord(item) - 97, a, b) + 97)
        for item in ciphered_text
    ]
    return "".join(list_char)
