from enum import Enum

def count_symbol(board, symbol):
    """Counts the number of occurrences of a symbol on the board.

    Args:
        board: A 2D list representing the board.
        symbol: The symbol to count.

    Returns:
        The number of times the symbol appears on the board.
    """
    return sum(row.count(symbol) for row in board)

def has_win(board, symbol):
    """Checks if there is a win condition for the given symbol.

    Args:
        board: A 2D list representing the board.
        symbol: The symbol to check for win.

    Returns:
        True if there is a win condition for the symbol, False otherwise.
    """
    
    dummy_row = symbol * 3
    if any(row == dummy_row for row in board): return True
    
    # check horizonal
    if any("".join(chars) == dummy_row for chars in zip(*board)): return True
    
    # check vertical
    if any("".join(chars) == dummy_row for chars in zip(*board)): return True
    
    # check diagonal 1
    if dummy_row == "".join(board[i][j] for i,j in zip(range(3), range(3))): return True
    
    # cehckdiagonal 2
    if dummy_row == "".join(board[i][j] for i,j in zip(range(2, -1, -1), range(3))): return True
    
    return False

def gamestate(board):
    """Simulates a game and determines the outcome."""
    
    x_count = count_symbol(board, "X")
    o_count = count_symbol(board, "O")
    if o_count > x_count:
        raise ValueError("Wrong turn order: O started")
    if (x_count - 2) >= o_count:
        raise ValueError("Wrong turn order: X went twice")
    
    x_win = has_win(board, "X")
    o_win = has_win(board, "O")
    if x_win and o_win:
        raise ValueError("Impossible board: game should have ended after the game was won")
    if x_win or o_win:
        return "win"
    return "draw" if (o_count + x_count) == 9 else "ongoing"

