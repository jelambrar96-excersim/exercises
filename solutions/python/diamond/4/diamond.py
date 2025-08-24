from string import ascii_uppercase

def rows(letter, simbol=" "):
    """
    Generates a diamond shape using the given letter and 
    a specified symbol.

    Args:
        letter (str): The uppercase letter to use as the center 
                      of the diamond.
        simbol (str, optional): The symbol to use for the diamond's 
                                padding. Defaults to a space.

    Returns:
        list: A list of strings representing the rows of the diamond shape.

    Raises:
        ValueError: If the given letter is not an uppercase ASCII character.
    """
    if not letter in ascii_uppercase:
        return ValueError("Invalid character")
    ind = ascii_uppercase.find(letter)

    out_list = []
    for i, item in enumerate(ascii_uppercase[0:ind + 1]):
        str1 = simbol * (ind - i)
        str2 = simbol * (2 * i - 1) 
        if i == 0:
            element = str1 + item + str1 # fist row
        else:
            element = str1 + item + str2 + item + str1
        out_list.append(element)
    return out_list + out_list[:ind][::-1]
