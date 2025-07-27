TABLE = ["eggs", "peanuts", "shellfish", "strawberries", 
         "tomatoes", "chocolate", "pollen", "cats"]

class Allergies:

    def __init__(self, score):
        self.score = score

    def allergic_to(self, item):
        ind = TABLE.index(item)
        return (self.score | 2**ind) == self.score

    @property
    def lst(self):
        return [ item for i, item in enumerate(TABLE) if (self.score | 2**i) == self.score ]
