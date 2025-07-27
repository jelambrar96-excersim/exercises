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
        self.coordinates = (x_pos, y_pos)
        self.direction = direction

    def move(self, moviment):
        for mv in moviment:
            if mv == "L":
                self.direction += 1
                self.direction %= 4
            if mv == "R":
                self.direction -= 1
                self.direction %= 4
            if mv == "A":
                self.coordinates = (
                    self.coordinates[0] + MV_TABLE[self.direction][0],
                    self.coordinates[1] + MV_TABLE[self.direction][1]
                )