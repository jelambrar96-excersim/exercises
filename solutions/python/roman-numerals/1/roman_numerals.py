ROMANS = [("V", "I"), ("L", "X"), ("D", "C"), ("", "M"),]

def roman(number):
    if number > 3999 or number < 1:
        raise ValueError("Invalid number")
    out = ""
    for i, (five, one) in enumerate(ROMANS):
        last_digit = number % 10
        temp = ""
        if last_digit > 8:
            temp = one + ROMANS[i+1][1]
        elif last_digit > 5:
            temp = five + one * (last_digit - 5)
        elif last_digit == 5:
            temp = five
        elif last_digit == 4:
            temp = one + five
        else:
            temp = one * last_digit
        out = temp + out
        number //= 10
        if number == 0:
            break
    return out 
        
