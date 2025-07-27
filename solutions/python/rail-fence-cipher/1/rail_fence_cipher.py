
def b_message(message, rails):
    index_list = list(range(rails)) + list(range(rails - 2, 0, -1))
    len_index_list = len(index_list)
    # print(index_list)
    board = [[None] * len(message) for item in range(rails) ]
    for i, item in enumerate(message):
        board[index_list[i%len_index_list]][i] = item
    return board


def encode(message, rails):
    if rails == 2:
        return "".join([ "".join([ item0 for i, item0 in enumerate(message) if i % rails == n]) for n in range(rails) ])
    board = b_message(message=message, rails=rails)
    # print(*board, sep='\n')
    return "".join(["".join([ item for item in row if item is not None] ) for row in board])


def decode(encoded_message, rails):
    len_message = len(encoded_message)
    list_base = list(range(len_message))
    board = b_message(list_base, rails)
    
    print(*board, sep="\n")
    flatten_board = []
    for row in board:
        flatten_board.extend([item for item in row if item is not None])
    original_message = [ None ] * len_message
    for ind, item in zip(flatten_board, encoded_message):
        original_message[ind] = item
    return "".join(original_message)

