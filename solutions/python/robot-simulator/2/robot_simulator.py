# Globals for the directions
# Change the values as you see fit
EAST = 0
NORTH = 1
WEST = 2
SOUTH = 3

MV_TABLE = [
    (1, 0),
    (0, 1),
    (-1, 0),
    (0, -1)
]

class Robot:
    def __init__(self, direction=NORTH, x_pos=0, y_pos=0):
        self._x, self._y = x_pos, y_pos
        self.direction = direction

    @property
    def coordinates(self):
        return (self._x, self._y)

    def move(self, moviment):
        for mv in moviment:
            if mv == "L":
                self.direction += 1
                self.direction %= 4
            if mv == "R":
                self.direction -= 1
                self.direction %= 4
            if mv == "A":
                self._x += MV_TABLE[self.direction][0]
                self._y += MV_TABLE[self.direction][1]
