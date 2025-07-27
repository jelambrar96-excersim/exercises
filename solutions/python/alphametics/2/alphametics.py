from itertools import combinations, permutations
from string import ascii_uppercase


def solve(puzzle):
    
    puzzle = puzzle.replace(" ", "")
    letters = set([ item for item in puzzle if item in ascii_uppercase ])
    len_letters = len(letters)

    for comb in permutations("0123456789", len_letters):

        temp_puzzle = puzzle

        for l, n in zip(letters, comb):
            temp_puzzle = temp_puzzle.replace(l, n)

        sumandos, suma = temp_puzzle.split("==")
        
        if suma[0] == "0":
            continue

        lista_sumandos = sumandos.split("+")
        if any([ item[0] == "0" for item in lista_sumandos]):
            continue

        suma_sumandos = sum(int(item) for item in lista_sumandos)
        entero_suma = int(suma)

        if suma_sumandos == entero_suma:
            return dict((l,int(n)) for l,n in zip(letters, comb))

