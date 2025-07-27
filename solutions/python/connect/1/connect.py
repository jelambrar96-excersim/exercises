# Function to get all valid neighboring positions (children) around a given position
def get_children(pos, size):
    # Generate x and y ranges centered at pos[0] and pos[1] respectively
    posx = [item for item in range(pos[0] - 1, pos[0] + 2)]
    posy = [item for item in range(pos[1] - 1, pos[1] + 2)]

    # Create combinations of x and y excluding diagonals (i != j filters out diagonals)
    pos_product = [(x, y) for j, x in enumerate(posx) for i, y in enumerate(posy) if i != j]

    # Filter positions that are within the board boundaries
    return [(x, y) for x, y in pos_product if (0 <= x < size[0]) and (0 <= y < size[1])]


# Recursive function to determine if there is a path from the top to bottom
# for the given symbol, starting from the given position
def path_winner(board, position, symbol, visited=None):
    num_rows = len(board)
    num_cols = len(board[0])

    # If the current position does not contain the player's symbol, abort path
    if board[position[1]][position[0]] != symbol:
        return None

    # Initialize visited path
    if visited is None:
        visited = list()

    visited.append(position)

    # If we've reached the last row, a winning path is found
    if position[1] == num_rows - 1:
        return visited

    # Explore all valid neighboring positions
    for c in get_children(position, (num_cols, num_rows)):
        if c in visited:
            continue  # Avoid cycles
        result_c = path_winner(board, c, symbol, visited)
        if result_c is not None:
            return result_c  # Return the path if a win is found

    # Backtrack if no valid path continues from here
    visited.remove(position)
    return None


# Utility function to transpose the board (rows <-> columns)
def transpose(board):
    return [list(item) for item in zip(*board)]


# ConnectGame class encapsulating the game logic
class ConnectGame:
    def __init__(self, board):
        # Clean up board input and parse into 2D list of characters
        rows = board.replace(" ", "").split("\n")
        self.board = [[item for item in row] for row in rows]

    def get_winner(self):
        num_cols = len(self.board[0])

        # Check for 'O' player win (top to bottom)
        for i in range(num_cols):
            res = path_winner(self.board, (i, 0), 'O', None)
            if res is not None:
                return 'O'

        # Check for 'X' player win (left to right) by transposing the board
        board_transposed = transpose(self.board)
        num_cols_tr = len(board_transposed[0])
        for i in range(num_cols_tr):
            res = path_winner(board_transposed, (i, 0), 'X', None)
            if res is not None:
                return 'X'

        # No winner found
        return ""
