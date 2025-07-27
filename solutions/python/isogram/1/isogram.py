from collections import Counter
from string import ascii_lowercase

def is_isogram(string):
    count_dict = Counter(item for item in string.lower() if item in ascii_lowercase)
    return all(item == 1 for item in count_dict.values())
