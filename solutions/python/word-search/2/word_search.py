class Point:
    def __init__(self, x, y):
        self.x = x
        self.y = y

    def __eq__(self, other):
        return (self.x == other.x) and (self.y == other.y)

    def __str__(self):
        return f"Point({self.x}, {self.y})"


class WordSearch:
    def __init__(self, puzzle):
        self.board = [ [ item for item in row ] for row in puzzle]
        self.h = len(self.board)
        self.w = len(self.board[0])

    def search(self, word):

        # list of chars
        word_items = [ item for item in word]
        len_word = len(word_items)
        fisrt_char = word_items[0]

        # loop board
        for y,row in enumerate(self.board):
            for x,item in enumerate(row):
                
                if item != fisrt_char:
                    continue

                if self.searchlr(y,x,word_items):
                    found_tuple = (Point(x,y), Point(x + len_word - 1, y))
                    return found_tuple

                if self.searchrl(y,x, word_items):
                    found_tuple = (Point(x,y), Point(x - len_word + 1, y))
                    return found_tuple

                if self.searchud(y,x,word_items):
                    found_tuple = (Point(x,y), Point(x, y + len_word - 1))
                    return found_tuple

                if self.searchdu(y,x, word_items):
                    found_tuple = (Point(x,y), Point(x, y - len_word + 1))
                    return found_tuple
                
                if self.search_tl_br(y,x, word_items):
                    found_tuple = (Point(x,y), Point(x + len_word - 1, y + len_word - 1))
                    return found_tuple

                if self.searchd_bl_tr(y,x, word_items):
                    found_tuple = (Point(x,y), Point(x + len_word - 1, y - len_word + 1))
                    return found_tuple

                if self.searchd_tr_bl(y,x, word_items):
                    found_tuple = (Point(x,y), Point(x - len_word + 1, y + len_word - 1))
                    return found_tuple

                if self.searchd_br_tl(y,x, word_items):
                    found_tuple = (Point(x,y), Point(x - len_word + 1, y - len_word + 1))
                    return found_tuple

        return None

    
    def searchlr(self, y, x, word_items):
        for i,c in enumerate(word_items):
            if (i + x) >= self.w:
                return False
            if c != self.board[y][x + i]:
                return False
        return True


    def searchrl(self, y, x, word_items):
        for i,c in enumerate(word_items):
            if (x - i) < 0:
                return False
            if c != self.board[y][x - i]:
                return False
        return True


    def searchud(self, y, x, word_items):
        for i,c in enumerate(word_items):
            if (i + y) >= self.h:
                return False
            if c != self.board[y + i][x]:
                return False
        return True


    def searchdu(self, y, x, word_items):
        for i,c in enumerate(word_items):
            if (y - i) < 0:
                return False
            if c != self.board[y - i][x]:
                return False
        return True

    
    def search_tl_br(self, y, x, word_items):
        for i,c in enumerate(word_items):
            if (x + i) >= self.w:
                return False
            if (y + i) >= self.h:
                return False
            if c != self.board[y + i][x + i]:
                return False
        return True


    def searchd_bl_tr(self, y, x, word_items):
        for i,c in enumerate(word_items):
            if (x + i) >= self.w:
                return False
            if (y - i) < 0:
                return False
            if c != self.board[y - i][x + i]:
                return False
        return True


    def searchd_tr_bl(self, y, x, word_items):
        for i,c in enumerate(word_items):
            if (x - i) < 0:
                return False
            if (y + i) >= self.h:
                return False
            if c != self.board[y + i][x - i]:
                return False
        return True


    def searchd_br_tl(self, y, x, word_items):
        for i,c in enumerate(word_items):
            if (x - i) < 0:
                return False
            if (y - i) < 0:
                return False
            if c != self.board[y - i][x - i]:
                return False
        return True

