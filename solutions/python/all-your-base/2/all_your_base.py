
def rebase(input_base, list_digit, output_base):

    if input_base < 2:
        raise ValueError("input base must be >= 2")

    if output_base < 2:
        raise ValueError("output base must be >= 2")

    if not all((item < input_base and item >= 0) for item in list_digit):
        raise ValueError("all digits must satisfy 0 <= d < input base")

    num = sum( item * (input_base ** i) for i, item in enumerate(reversed(list_digit)))
    print(num)

    if num == 0:
        return [0]

    list_out_digits = []
    while num > 0:
        list_out_digits.append(num % output_base)
        num //= output_base

    return list(reversed(list_out_digits))
