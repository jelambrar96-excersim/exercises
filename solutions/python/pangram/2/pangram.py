from string import ascii_lowercase

def is_pangram(sentence):
    # return len(ascii_lowercase) == len(set(sentence.lower()) & set(ascii_lowercase))
    return set(ascii_lowercase).issubset(set(sentence.lower()))