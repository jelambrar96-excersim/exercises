from collections import Counter

# Score categories.
# Change the values as you see fit.
YACHT = 0
ONES = 1
TWOS = 2
THREES = 3
FOURS = 4
FIVES = 5
SIXES = 6
FULL_HOUSE = 7
FOUR_OF_A_KIND = 8
LITTLE_STRAIGHT = 9
BIG_STRAIGHT = 10
CHOICE = 11

YACHT_LIST = [ONES, TWOS, THREES, FOURS, FIVES, SIXES]

def score(dice, category):
    dice_counter = Counter(dice)
    if category == YACHT:
        return 50 if any(v == 5 for v in dict(dice_counter).values()) else 0
    if category in YACHT_LIST:
        cat_index = YACHT_LIST.index(category) + 1
        return cat_index* dice_counter[cat_index]
    if category == FULL_HOUSE:
        return 0 if set(v for v in dict(dice_counter).values()) ^ {2,3} else sum(dice)
    if category == LITTLE_STRAIGHT:
        return 30 if sorted(dice) == [1,2,3,4,5] else 0
    if category == BIG_STRAIGHT:
        return 30 if sorted(dice) == [2,3,4,5,6] else 0
    if category == CHOICE:
        return sum(dice)
    if category == FOUR_OF_A_KIND:
        return sum( 4 * k for k,v in dict(dice_counter).items() if v >= 4)
    raise ValueError("Invalid Category")


