def spiral_matrix(size, start=1):
    if size == 0:
        return []
    board = [ [start for __ in range(size)] for __ in range(size) ]

    direccion = [(0, 1), (1, 0), (0, -1), (-1, 0)]

    pos = [0,size-1]
    pos_dir = 0

    counter  =  start
    max_counter = size * size  + start

    for i in range(size):
        board[0][i] = counter
        counter += 1

    while counter < max_counter:
        size -= 1
        for i in range(2):
            pos_dir += 1
            pos_dir %= 4
            for i in range(size):
                pos[0] += direccion[pos_dir][0]
                pos[1] += direccion[pos_dir][1]
                board[pos[0]][pos[1]] = counter
                counter += 1

    return board 
