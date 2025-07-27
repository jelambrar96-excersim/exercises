from itertools import pairwise


def rows_iterative(row_count):
    if row_count < 0:
        raise ValueError("number of rows is negative")
    size = row_count + 1
    board = [ [0] * size for __ in range(size)]
    for i in range(size):
        board[i][0] = 1
    for i in range(1, size):
        for j in range(1, i + 1):
            board[i][j] = board[i - 1][j] + board[i - 1][j - 1] 
    return [board[i][:i+1] for i in range(row_count)]



def rows(row_count):
    if row_count < 0:
        raise ValueError("number of rows is negative")
    if row_count == 0:
        return []
    if row_count == 1:
        return [[1]]
    temp_triangle = rows(row_count - 1)
    last_row = [1] + [ sum(item) for item in pairwise(temp_triangle[-1])] + [1]
    temp_triangle.append(last_row)
    return temp_triangle