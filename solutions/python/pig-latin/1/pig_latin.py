
VOWELS = {'a', 'e', 'i', 'o', 'u'}


def aux_check(letter, text):
    ind = text.find(letter)
    if ind == -1:
        return -1
    if len(set(text[:ind]) & VOWELS) == 0:
        return ind
    return -1


def translate_word(text):
    # rule 1
    if text[0] in VOWELS or text[:2] in ('xr', 'yt'):
        return text + 'ay'
    # rule 3
    qu_check = aux_check('qu', text)
    if qu_check >= 0:
        return text[qu_check + 2:] + text[:qu_check + 2] + 'ay'
    # y is treated like a consonant at the begining of a word 
    if text[0] == 'y' and text[1] in VOWELS:
        return text[1:] + text[0] + 'ay'
    # rule 4
    y_check = aux_check('y', text)
    if y_check >= 0:
        if len(text) == 2 and y_check == 1:
            return text[1] + text[0] + 'ay'
        return text[y_check:] + text[:y_check] + 'ay'
    # rule ch
    if text[:3] in ('thr', 'sch'):
        return text[3:] + text[:3] + 'ay'
    if text[:2] in ('ch', 'th'):
        return text[2:] + text[:2] + 'ay'
    # rule 2
    return text[1:] + text[0] + 'ay'


def translate(text):
    return " ".join([ translate_word(item) for item in text.split(" ")])

