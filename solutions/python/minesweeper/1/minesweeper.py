MINE_CHAR = "*"
SAFE_CHAR = " "

def get_h(board):
    return len(board)

def get_w(board):
    widths = set(len(rows) for rows in board)
    if len(widths) > 1:
        raise ValueError("The board is invalid with current input.")
    return widths.pop()

def check_board(board):
    single_string = "".join(board)
    set_board = set(single_string)
    if len(set_board) > 2:
        raise ValueError("The board is invalid with current input.")
    for item in set_board:
        if not item in [SAFE_CHAR, MINE_CHAR]:
            raise ValueError("The board is invalid with current input.")

def annotate(board):
    output = []
    h = get_h(board)
    if h == 0:
        return []
    w = get_w(board)
    check_board(board)

    mines_counter = [ [0] * (w + 2) for __ in range(h + 2)]

    for i, row in enumerate(board):
        for j, item in enumerate(row):

            if item == MINE_CHAR:

                mines_counter[i + 0][j + 0] += 1
                mines_counter[i + 0][j + 1] += 1
                mines_counter[i + 0][j + 2] += 1
                
                mines_counter[i + 1][j + 0] += 1
                # mines_counter[i + 1][j + 1] = -1
                mines_counter[i + 1][j + 2] += 1
    
                mines_counter[i + 2][j + 0] += 1
                mines_counter[i + 2][j + 1] += 1
                mines_counter[i + 2][j + 2] += 1

    mines_counter_cropped = [ [item for item in row[1:-1] ] \
                              for row in mines_counter[1:-1] ]

    ouput = []
    for i, row in enumerate(board):
        new_row = [ (MINE_CHAR if item == MINE_CHAR else str(mines_counter_cropped[i][j])) \
                  for j, item in enumerate(row)]
        output.append("".join(new_row).replace("0", " "))
    return output
    