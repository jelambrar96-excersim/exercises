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
    if len_isbn == 9:
        new_isbn += "0"
    # convert to list and replace X
    digits = list(new_isbn)
    if digits[-1] == "X":
        digits[-1] = "10"
    # check
    if not all(c.isdigit() for c in digits):
        return False
    return sum(int(c) * i for i, c in enumerate(reversed(digits), start=1)) % 11 == 0
