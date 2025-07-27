from random import randint
from string import ascii_lowercase

class Cipher:
    def __init__(self, key=None):
        if key is None:
            self._key = [ randint(0, len(ascii_lowercase) -1) for __ in range(128)]
        else:
            self._key = [ ascii_lowercase.find(item) for item in key ]
        self.len_key = len(self._key)

    def encode(self, text):
        out = ""
        low_ind = 0
        len_text = len(text)
        while low_ind < len_text:
            high_ind = min(len_text, low_ind + self.len_key)
            temp_text = text[low_ind:high_ind]
            for i,item in enumerate(temp_text):
                new_item = ascii_lowercase[(ascii_lowercase.find(item) + self._key[i]) % len(ascii_lowercase)]
                out = out + new_item
            low_ind = high_ind
        return out


    def decode(self, text):
        out = ""
        low_ind = 0
        len_text = len(text)
        while low_ind < len_text:
            high_ind = min(len_text, low_ind + self.len_key)
            temp_text = text[low_ind:high_ind]
            for i,item in enumerate(temp_text):
                new_item = ascii_lowercase[(ascii_lowercase.find(item) - self._key[i]) % len(ascii_lowercase)]
                out = out + new_item
            low_ind = high_ind
        return out

    @property
    def key(self):
        return "".join(ascii_lowercase[item] for item in self._key)