class Queen:
    def __init__(self, row, column):
        if row < 0:
            raise ValueError("row not positive")
        if row > 7:
            raise ValueError("row not on board")
        if column < 0:
            raise ValueError("column not positive")
        if column > 7:
            raise ValueError("column not on board")
        self.row = row
        self.col = column

    def can_attack(self, another_queen):
        if another_queen.row == self.row and another_queen.col == self.col:
            raise ValueError("Invalid queen position: both queens in the same square")
        if another_queen.row == self.row:
            return True
        if another_queen.col == self.col:
            return True
        if (self.col - self.row) == (another_queen.col - another_queen.row):
            return True
        if (self.col + self.row) == (another_queen.col + another_queen.row):
            return True
        return False