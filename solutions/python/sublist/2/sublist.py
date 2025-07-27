"""
This module defines enumerated constants and a function to classify the 
relationship between two lists: whether one is a sublist, superlist, equal 
to, or unequal to the other.

Enumerated constants are used to represent these relationships in a 
memory-efficient and expressive way.

For more on enumerated types: https://en.wikipedia.org/wiki/Enumerated_type
"""

# Possible sublist categories
# Constants to represent relationship types
EQUAL = 1        # Lists are exactly the same
SUBLIST = 2      # First list is a sublist of the second
SUPERLIST = 4    # First list is a superlist of the second
UNEQUAL = 0      # Lists are different and not sub/superlists

def sublist(list_one, list_two):
    """
    Determines the relationship between two lists.

    Parameters
    ----------
    list_one : list
        The first list to compare.
    list_two : list
        The second list to compare.

    Returns
    -------
    int
        One of the constants EQUAL, SUBLIST, SUPERLIST, or UNEQUAL,
        depending on the relationship between the two lists.

    Logic
    -----
    - If the lists are equal in length:
        - If both are empty: return EQUAL.
        - If contents are equal: return EQUAL, else return UNEQUAL.
    - If list_one is shorter:
        - If it's empty: it's a sublist.
        - Otherwise, check if list_one matches any sub-slice of list_two.
    - If list_one is longer:
        - Reuse the function recursively by swapping the arguments.
        - If list_two is a sublist of list_one, then list_one is a superlist.
    - Otherwise: return UNEQUAL.
    """
    len_list_one = len(list_one)
    len_list_two = len(list_two)

    # Case: lists are of equal length
    if len_list_one == len_list_two:
        if len_list_one == 0:
            return EQUAL  # Both lists are empty
        return EQUAL if list_one == list_two else UNEQUAL

    # Case: list_one is shorter (check if it's a sublist of list_two)
    if len_list_one < len_list_two:
        if len_list_one == 0:
            return SUBLIST  # Empty list is sublist of any list
        # Slide window over list_two to check for matching sublist
        if any(list_one == list_two[i:i + len_list_one] for i in range(len_list_two - len_list_one + 1)):
            return SUBLIST
        return UNEQUAL

    # Case: list_one is longer (check if list_two is a sublist of list_one)
    if sublist(list_two, list_one) == SUBLIST:
        return SUPERLIST

    return UNEQUAL
