import math

from itertools import islice
from string import ascii_lowercase


def batched(iterable, n, *, strict=False):
    # batched('ABCDEFG', 3) → ABC DEF G
    if n < 1:
        raise ValueError('n must be at least one')
    iterator = iter(iterable)
    while batch := tuple(islice(iterator, n)):
        if strict and len(batch) != n:
            raise ValueError('batched(): incomplete batch')
        yield batch


def transpose(list_list):
    return [ list(item) for item in zip(*list_list) ]


def cipher_text(plain_text):
    
    # normalize
    normalized_text = "".join(item for item in plain_text.lower() if (item in ascii_lowercase) or (item in "0123456789"))
    len_nomalized  = len(normalized_text)

    # compute dimentions
    rs = math.sqrt(len_nomalized)
    cols = int(math.ceil(rs))
    rows = int(round(rs))

    if len(normalized_text) == 0:
        return ""

    # batches
    batched_text = list([ "".join(item) for item in batched(normalized_text, cols) ])
    print(batched_text)
    
    # adjust
    len_last_batch = len(batched_text[-1])
    if  len_last_batch < cols:
        batched_text[-1] = batched_text[-1] + " "*(cols - len_last_batch)
    
    # transpose
    transposed_text = [ "".join(item) for item in transpose(batched_text) ]
    return " ".join(transposed_text)
