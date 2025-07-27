from string import ascii_uppercase
from random import shuffle
from itertools import product

len_letters = len(ascii_uppercase)
letters = [item for item in ascii_uppercase] 
shuffle(letters)
numbers = list(range(1000))
shuffle(numbers)

def generate_name():
    for l, n in product(range(len_letters**2), range(1000)):
        l1 = letters[l//len_letters]
        l0 = letters[l%len_letters]
        selected_number = numbers[n]
        yield f"{l1}{l0}{selected_number:03}"

class Robot:
    
    name_generator = generate_name()
    
    def __init__(self):
        self.name = next(Robot.name_generator)
    
    def reset(self):
        self.name = next(Robot.name_generator)
