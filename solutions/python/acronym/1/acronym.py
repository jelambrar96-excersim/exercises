from string import ascii_letters

def transform(text):
    out = ""
    for c in text:
        if c in ascii_letters:
            return c.upper()
    return ""

def abbreviate(words):
    list_words = [ transform(item) for item in words.replace('-',' ').split(" ") ]
    return "".join([ item[0].upper() for item in list_words if item])
    