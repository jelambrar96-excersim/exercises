from itertools import product


def zero_padding_matrix(matrix):
    h = len(matrix)
    w = 0 if h == 0 else len(matrix[0])
    new_matrix = [[0 for j in range(w + 2)] for i in range(h + 2)]
    for i, j in product(range(h), range(w)):
        new_matrix[i + 1][j + 1] = matrix[i][j]
    return new_matrix


def gol(matrix):
    h = len(matrix)
    w = 0 if h == 0 else len(matrix[0])
    new_matrix = [[0 for j in range(w - 2)] for i in range(h - 2)]
    for i0, j0 in product(range(h - 2), range(w - 2)):
        m_value = matrix[i0 + 1][j0 + 1]
        sumgol = sum(matrix[i1 + i0][j1 + j0] for i1, j1 in product(range(3), range(3))) - m_value
        new_matrix[i0][j0] = 1 if sumgol == 3 or (m_value == 1 and sumgol == 2) else 0
        pass
    return new_matrix 


def tick(matrix):
    zp_matrix = zero_padding_matrix(matrix)
    return gol(zp_matrix)
