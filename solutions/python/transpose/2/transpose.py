from itertools import zip_longest

def transpose(text):
    output = [ "".join(i).rstrip("*") for i in zip_longest(*text.split("\n"), fillvalue="*") ]
    result = "\n".join(output).replace("*", " ").strip()
    return result
