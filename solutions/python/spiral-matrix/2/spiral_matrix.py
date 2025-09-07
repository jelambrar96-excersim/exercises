DIRS = [(0, 1), (1, 0), (0, -1), (-1, 0)]


def is_valid(p, size):
    if p[0] < 0 or p[0] >= size:
        return False
    if p[1] < 0 or p[1] >= size:
        return False
    return True


def spiral_matrix(size, start=1):
    if size == 0:
        return []
    board = [ [None for __ in range(size)] for __ in range(size) ] 
    
    point = (0, -1)
    ind = 0

    for i in range(size * size):
        new_point = point[0] + DIRS[ind][0], point[1] + DIRS[ind][1]
        if not is_valid(new_point, size) or board[new_point[0]][new_point[1]] is not None:
            ind = (ind + 1) % 4
            new_point = point[0] + DIRS[ind][0], point[1] + DIRS[ind][1]
    
        board[new_point[0]][new_point[1]] = i + start
        point = new_point[:]        
    
    return board 

