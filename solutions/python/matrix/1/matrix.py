class Matrix:
    def __init__(self, matrix_string):
        self._matrix = []
        for row in matrix_string.split("\n"):
            self._matrix.append([int(item) for item in row.split(" ")])

    def row(self, index):
        return self._matrix[index - 1]

    def column(self, index):
        return [ row[index - 1] for row in self._matrix ]
