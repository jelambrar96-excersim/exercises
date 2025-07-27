def is_valid(isbn):
    # check by numbers of characters
    if not len(isbn) in (10, 13):
        return False
    # normalize
    new_isbn = isbn.replace("-", "").upper()
    # check by numbers of characters
    len_isbn = len(new_isbn)
    if not len_isbn in (9, 10):
        return False
    # add verification digit
    last_digit = "0"
    if len_isbn == 9:
        new_isbn += last_digit
    sumatory = 0
    for i, character in enumerate(reversed(new_isbn), start=1):
        if character == "X" and i == 1:
            sumatory += 10 
            continue
        if not character.isdigit():
            return False
        sumatory += int(character) * i
    return sumatory % 11 == 0
