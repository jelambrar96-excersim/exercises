from itertools import permutations

# HOUSES = [0, 1, 2, 3, 4]
COLORS = ["red", "ivory", "green", "yellow"] # blue
OWNERS = ["Englishman", "Spaniard", "Ukrainian", "Japanese"] # "Norwegian"
PETS =   ["horse", "dog", "snail", "fox", "zebra"]
DRINKS = ["water", "orange juice", "tea", "coffe"]  # milk
HOBBIE = ["play chess", "read", "dancing", "painter", "play football"]


class ProblemZebra():

    def __ini__(self):
        self.HOUSES = None
        self.COLORS = None
        self.OWNERS = None
        self.PETS = None
        self.DRINKS = None
        self.HOBBIE = None

    
    def solve(self):
        for temp_d_list in permutations(DRINKS):
            d_list = *temp_d_list[:2], "milk", *temp_d_list[2:]
            for temp_o_list in permutations(OWNERS):
                o_list = "Norwegian", *temp_o_list
                if o_list[d_list.index("tea")] != "Ukrainian":
                    continue
                for temp_c_list in permutations(COLORS):
                    c_list = *temp_c_list[:1], "blue", *temp_c_list[1:]
                    if o_list[c_list.index("red")] != "Englishman":
                        continue
                    index_green_house = c_list.index("green")
                    if d_list[index_green_house] != "coffe":
                        continue
                    if index_green_house == 0:
                        continue
                    if c_list[index_green_house - 1] != "ivory":
                        continue
                    for b_list in permutations(HOBBIE):
                        if c_list[b_list.index("painter")] != "yellow":
                            continue
                        if d_list[b_list.index("play football")] != "orange juice":
                            continue
                        if o_list[b_list.index("play chess")] != "Japanese":
                            continue
                        for p_list in permutations(PETS):
                            if b_list[p_list.index("snail")] != "dancing":
                                continue
                            if o_list[p_list.index("dog")] != "Spaniard":
                                continue
                            if abs(b_list.index("read") - p_list.index("fox")) != 1:
                                continue
                            if abs(b_list.index("painter") - p_list.index("horse")) != 1:
                                continue                      
                            self.COLORS = c_list
                            self.OWNERS = o_list
                            self.PETS = p_list
                            self.DRINKS = d_list
                            self.HOBBIE = b_list
                            return self

    def house(self, ind):
        return ind, self.COLORS[ind], self.OWNERS[ind], self.PETS[ind], self.DRINKS[ind], self.HOBBIE[ind]

    def drinks(self, d):
        ind = self.DRINKS.index(d)
        return ind, self.COLORS[ind], self.OWNERS[ind], self.PETS[ind], self.DRINKS[ind], self.HOBBIE[ind]

    def colors(self, d):
        ind = self.COLORS.index(d)
        return ind, self.COLORS[ind], self.OWNERS[ind], self.PETS[ind], self.DRINKS[ind], self.HOBBIE[ind]

    def owner(self, d):
        ind = self.OWNERS.index(d)
        return ind, self.COLORS[ind], self.OWNERS[ind], self.PETS[ind], self.DRINKS[ind], self.HOBBIE[ind]

    def pet(self, d):
        ind = self.PETS.index(d)
        return ind, self.COLORS[ind], self.OWNERS[ind], self.PETS[ind], self.DRINKS[ind], self.HOBBIE[ind]

    def hobbie(self, d):
        ind = self.HOBBIE.index(d)
        return ind, self.COLORS[ind], self.OWNERS[ind], self.PETS[ind], self.DRINKS[ind], self.HOBBIE[ind]


    def solution(self):
        return [ z for z  in zip(range(5), self.COLORS, self.OWNERS, self.PETS, self.DRINKS, self.HOBBIE)]



def drinks_water():
    pb = ProblemZebra().solve()
    return pb.drinks("water")[2]


def owns_zebra():
    pb = ProblemZebra().solve()
    return pb.pet("zebra")[2]
