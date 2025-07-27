def find(search_list, value):
    min_ind = 0
    max_ind = len(search_list) - 1
    while min_ind <= max_ind:
        middle_ind = (min_ind + max_ind) // 2
        if value > search_list[middle_ind]:
            min_ind = middle_ind + 1
        elif value < search_list[middle_ind]:
            max_ind = middle_ind - 1
        else:
            return middle_ind
    raise ValueError("value not in array")
