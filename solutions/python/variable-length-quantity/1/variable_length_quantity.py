vlq_num_mask = 0b1111111
vlq_continue_mask = 1<<7

def encode(numbers):
    # 1-liner 
    return [((number>>(7*i)) & vlq_num_mask) | (bool(i)<<7)
            for number in numbers
            for i in range(((number>>1).bit_length())//7, -1, -1)]

def decode(bytes_):
    return list(_decode(bytes_))

def _decode(bytes_):
    code = 0
    if bytes_[-1] & vlq_continue_mask:
        raise ValueError('incomplete sequence')

    for byte in bytes_:
        code = (code << 7)|(byte & vlq_num_mask)
        if not(byte & vlq_continue_mask):
            yield code
            code = 0

    
    