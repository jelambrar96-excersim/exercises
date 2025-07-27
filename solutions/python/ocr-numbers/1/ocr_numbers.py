
def check_grid(input_grid):
    len_input_grid = len(input_grid)
    if not len_input_grid % 4 == 0:
        raise ValueError("Number of input lines is not a multiple of four")
    len_lines = [ len(line) for line in input_grid ]
    if any(ll % 3 != 0 for ll in len_lines):
        raise ValueError("Number of input columns is not a multiple of three")
    avg_len_lines = sum(len_lines) // len_input_grid
    if any(ll != avg_len_lines for ll in len_lines):
        raise ValueError("Number of input columns is not the same on all lines")
    return len_input_grid, avg_len_lines


VLOOK_TABLE = [
    " _ | ||_|   ", # 0
    "     |  |   ", # 1
    " _  _||_    ", # 2
    " _  _| _|   ", # 3
    "   |_|  |   ", # 4
    " _ |_  _|   ", # 5
    " _ |_ |_|   ", # 6
    " _   |  |   ", # 7
    " _ |_||_|   ", # 8
    " _ |_| _|   ", # 9
]


def convert_row(row):
    w_row = len(row[0])
    output = []
    for w in range(0, w_row, 3):
        batch = [ item[w:w+3] for item in row]        
        ocr_key = "".join(batch)
        if not ocr_key in VLOOK_TABLE:
            output.append("?")
            continue
        ind = VLOOK_TABLE.index(ocr_key)
        output.append(str(ind))
    return "".join(output)


def convert(input_grid):
    h_grid, w_grid = check_grid(input_grid)
    output = []
    for h in range(0, h_grid, 4):
        temp_grid = input_grid[h:h+4]
        value = convert_row(temp_grid)
        output.append(value)
    return ",".join(output)
    