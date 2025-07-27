def is_armstrong_number(number):
    digits = []
    n = number
    while n > 0:
        digits.append(n % 10)
        n //= 10
    l =len(digits)
    return number == sum([ item ** l for item in digits])
