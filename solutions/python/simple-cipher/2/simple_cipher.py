from random import randint
from string import ascii_lowercase

class Cipher:
    
    def __init__(self, key=None):
        self._key = [ randint(0, len(ascii_lowercase) - 1) for __ in range(100)] \
                    if (key is None) else [ ascii_lowercase.find(item) for item in key ]


    def encode(self, text):
        lenkey = len(self._key)
        m = len(ascii_lowercase)
        out = "".join(
            ascii_lowercase[(ascii_lowercase.find(item) + self._key[i % lenkey]) % m] 
            for i, item in enumerate(text)
        )
        return out


    def decode(self, text):
        lenkey = len(self._key)
        m = len(ascii_lowercase)
        out = "".join(
            ascii_lowercase[(ascii_lowercase.find(item) - self._key[i % lenkey] + m) % m] 
            for i, item in enumerate(text)
        )
        return out

    @property
    def key(self):
        return "".join(ascii_lowercase[item] for item in self._key)
