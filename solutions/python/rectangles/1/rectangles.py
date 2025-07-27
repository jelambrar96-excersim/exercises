VERTICE_CHAR = '+'
H_CHAR = '-'
V_CHAR = '|'


def isrectangle(list_string):
    if len(list_string) < 2:
        return False
    set_w = set(len(row) for row in list_string)
    if len(set_w) > 1:
        return False
    w = set_w.pop()
    if w < 2:
        return False
    if not list_string[0][0] == VERTICE_CHAR:
        return False
    if not list_string[0][-1] == VERTICE_CHAR:
        return False
    if not list_string[-1][0] == VERTICE_CHAR:
        return False
    if not list_string[-1][-1] == VERTICE_CHAR:
        return False
    if not all(item in (H_CHAR, VERTICE_CHAR) for item in list_string[0][1:-1]):
        return False
    if not all(item in (H_CHAR, VERTICE_CHAR) for item in list_string[-1][1:-1]):
        return False
    if not all(item[0] in (V_CHAR, VERTICE_CHAR) for item in list_string[1:-1]):
        return False
    if not all(item[-1] in (V_CHAR, VERTICE_CHAR) for item in list_string[1:-1]):
        return False
    return True
    

def rectangles(strings):
    h =len(strings)
    if h < 2:
        return 0
    set_w = set(len(row) for row in strings)
    if len(set_w) > 1:
        return 0
    w = set_w.pop()
    if w < 2:
        return 0
    # list_rectangles = []
    num_rectangles = 0
    for i, row in enumerate(strings):
        for j, item in enumerate(row):
            if item != VERTICE_CHAR:
                continue
            for ii in range(i + 1, h):
                for jj in range(j + 1, w):
                    if strings[ii][jj] != VERTICE_CHAR:
                        continue
                    temp_rectangle = [row[j:jj+1] for row in strings[i:ii+1]]
                    flag = isrectangle(temp_rectangle)
                    if flag:
                        # list_rectangles.append(temp_rectangle)
                        num_rectangles += 1
                    # print(temp_rectangle, flag)
    return num_rectangles

