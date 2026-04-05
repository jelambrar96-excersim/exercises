def line_up_number(number):
    mod10_number = number % 10
    mod100_number = number % 100
    if mod10_number == 1 and mod100_number != 11:
        return "st"
    if mod10_number == 2 and mod100_number != 12:
        return "nd"
    if mod10_number == 3 and mod100_number != 13:
        return "rd"
    return "th"


def line_up(name, number):
    return f"{name}, you are the {number}{line_up_number(number)} customer we serve today. Thank you!"
