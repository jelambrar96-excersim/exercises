

def check_matrix(matrix):
    n_rows = len(matrix)
    len_rows = [ len(row) for row in matrix ]
    avg_len = sum(len_rows) // n_rows
    if any(item != avg_len for item in len_rows):
        raise ValueError("irregular matrix")


def saddle_points(matrix):
    h = len(matrix)
    if h == 0:
        return []
    check_matrix(matrix)
    w = len(matrix[0])
    max_rows = [ max(row) for row in matrix ]
    min_col = [ min(col) for col in zip(*matrix) ]
    list_saddle_points = []
    for i in range(h):
        for j in range(w):
            if max_rows[i] == min_col[j]:
                list_saddle_points.append({"row": i + 1, "column": j + 1})
    return list_saddle_points