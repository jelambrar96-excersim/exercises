from string import ascii_lowercase


len_ascii_lowercase = len(ascii_lowercase)
chunk_size = 5

def aux_encode(item):
    ind = ascii_lowercase.find(item)
    if ind == -1:
        if item in "0123456789":
            return item
        return ""
    return ascii_lowercase[len_ascii_lowercase - ind - 1]


def encode(plain_text):
    new_text = [ aux_encode(item) for item in plain_text.lower() if item in ascii_lowercase or item in "0123456789"]
    batches = [ "".join(new_text[i:i+chunk_size]) for i in range(0, len(new_text), chunk_size) ]
    return " ".join(batches)


def decode(ciphered_text):
    new_text = [ aux_encode(item) for item in ciphered_text.lower() ]
    return "".join(new_text)
