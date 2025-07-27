from string import ascii_lowercase, ascii_uppercase

def translate(character, key):
    if character in ascii_lowercase:
        return ascii_lowercase[(ascii_lowercase.index(character) + key) % len(ascii_lowercase)]
    if character in ascii_uppercase:
        return ascii_uppercase[(ascii_uppercase.index(character) + key) % len(ascii_uppercase)]
    return character

def rotate(text, key):
    return "".join(translate(item, key) for item in text)
