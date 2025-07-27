VLOOK_TABLE = [0,2,4,6,8,1,3,5,7,9]

class Luhn:
    def __init__(self, card_num):
        self.card_num = card_num
    
    def valid(self):
        card_str = self.card_num.replace(' ', '')
        if len(card_str) <= 1:
            return False
        if not all([ item.isdigit() for item in card_str ]):
            return False
        list_number = [int(item) for item in card_str[::-1]]
        suma = sum(list_number[::2])
        suma2 = sum(VLOOK_TABLE[item] for item in list_number[1::2])
        return (suma + suma2) % 10 == 0
