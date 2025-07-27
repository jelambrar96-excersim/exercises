from itertools import product
from collections import defaultdict

WHITE = 'W'
BLACK = 'B'
NONE = ' '
STONES = {WHITE, BLACK, NONE}

DELTAS = [(1, 0), (-1, 0), (0, 1), (0, -1)]


class Board:
    def __init__(self, board):
        self.rows = board
        self.columns = list(zip(*board))

    def onboard(self, x, y):
        return 0 <= x < len(self.columns) and 0 <= y < len(self.rows)

    def territory(self, x, y):
        if not self.onboard(x, y):
            raise ValueError("Invalid coordinate")

        stack = [(x, y)]
        territory = set()
        stones = set()

        while stack:
            x, y = stack.pop()
            if (x, y) not in territory and self.onboard(x, y):
                stone = self.columns[x][y]
                if stone == NONE:
                    territory.add((x, y))
                    stack += [(x+dx, y+dy) for dx, dy in DELTAS]
                else:
                    stones.add(stone)

        if len(stones) == 1 and len(territory) > 0:
            return (stones.pop(), territory)
        else:
            return (NONE, territory)

    def territories(self):
        territories = defaultdict(set)

        for (x, y) in product(range(0, len(self.columns)), range(0, len(self.rows))):
            stone, territory = self.territory(x, y)
            territories[stone] |= territory

        return {
            stone: territories.get(stone) if territories.get(stone) else set()
            for stone in STONES
        }
