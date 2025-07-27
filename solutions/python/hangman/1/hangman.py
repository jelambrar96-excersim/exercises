# Game status categories
# Change the values as you see fit
STATUS_WIN = 'win'
STATUS_LOSE = 'lose'
STATUS_ONGOING = 'ongoing'


class Hangman:
    def __init__(self, word):
        self.remaining_guesses = 9
        self.status = STATUS_ONGOING
        self.list_chars = [ item for item in word ]
        self.mask = [ False for item in word ]
        self.chars_used = set()

    def guess(self, char):
        if self.status != STATUS_ONGOING:
            raise ValueError("The game has already ended.")
        if char in self.list_chars and not char in self.chars_used:
            self.mask = [ m or (c == char) for m, c in zip(self.mask, self.list_chars)]
            if all(self.mask):
                self.status = STATUS_WIN
        else:
            self.remaining_guesses -= 1
            if self.remaining_guesses < 0:
                self.status = STATUS_LOSE
        self.chars_used.add(char)

    def get_masked_word(self):
        temp_list_str = [ c if m else "_" for m, c in zip(self.mask, self.list_chars)] 
        return "".join(temp_list_str)


    def get_status(self):
        return self.status
