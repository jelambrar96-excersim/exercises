

set_numbers = {str(i) for i in range(10)}
punctutaction = ",?!:;"

class PhoneNumber:
    def __init__(self, number):

        if any([item.isalpha() for item in number]):
            raise ValueError("letters not permitted")

        if any([item in punctutaction for item in number]):
            raise ValueError("punctuations not permitted")
        
        only_numbers = [ item for item in number if item in set_numbers ]
        len_only_numbers = len(only_numbers)
        
        if len_only_numbers < 10:
            raise ValueError("must not be fewer than 10 digits")
        if len_only_numbers > 11:
            raise ValueError("must not be greater than 11 digits")

        if len_only_numbers == 11 and only_numbers[0] != "1":
            raise ValueError("11 digits must start with 1")

        temp_number = "".join(only_numbers[len_only_numbers - 10:])
        area_code = temp_number[:3]
        if area_code[0] == "1":
            raise ValueError("area code cannot start with one")
        if area_code[0] == "0":
            raise ValueError("area code cannot start with zero")

        exchange_code = temp_number[3:]
        if exchange_code[0] == "0":
            raise ValueError("exchange code cannot start with zero")
        if exchange_code[0] == "1":
            raise ValueError("exchange code cannot start with one")

        self.number = temp_number
        self.area_code = area_code
        self.exchange_code = exchange_code

    def pretty(self):
        return f"({self.area_code})-{self.number[3:6]}-{self.number[6:]}"