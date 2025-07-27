"""
This exercise stub and the test suite contain several enumerated constants.

Enumerated constants can be done with a NAME assigned to an arbitrary,
but unique value. An integer is traditionally used because it’s memory
efficient.
It is a common practice to export both constants and functions that work with
those constants (ex. the constants in the os, subprocess and re modules).

You can learn more here: https://en.wikipedia.org/wiki/Enumerated_type
"""

# Possible sublist categories.
# Change the values as you see fit.
SUBLIST = 2
SUPERLIST = 4
EQUAL = 1
UNEQUAL = 0


def sublist(list_one, list_two):
    len_list_one = len(list_one)
    len_list_two = len(list_two)

    if len_list_one == 0 and len_list_two == 0:
        return EQUAL
    if len_list_one > 0 and len_list_two == 0:
        return SUPERLIST
    if len_list_one == 0 and len_list_two > 0:
        return SUBLIST

    if len_list_one == len_list_two:
        if all(item_one == item_two for item_one, item_two in zip(list_one, list_two)):
            return EQUAL
    
    for ind in [ i for i, item in enumerate(list_two) if item == list_one[0]]:
        if (ind + len_list_one) > len_list_two:
            break
        sub_list_two = list_two[ind:ind + len_list_one]
        if all(item_one == item_two for item_one, item_two in zip(list_one, sub_list_two)):
            return SUBLIST

    for ind in [ i for i, item in enumerate(list_one) if item == list_two[0]]:
        if (ind + len_list_two) > len_list_one:
            break
        sub_list_one = list_one[ind:ind + len_list_two]
        if all(item_two == item_one for item_two, item_one in zip(list_two, sub_list_one)):
            return SUPERLIST

    return UNEQUAL
