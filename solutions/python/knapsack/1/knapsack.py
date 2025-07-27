from itertools import combinations


def maximum_value(maximum_weight, items):
    if maximum_weight <= 0:
        raise ValueError()
    
    if len(items) == 0:
        return 0
    
    best_value = 0
    for i in range(1, len(items)):
        for comb in combinations(items, i):
            weight_comb = sum(item["weight"] for item in comb) 
            if weight_comb > maximum_weight:
                continue
            value_comb = sum(item["value"] for item in comb)
            if value_comb > best_value:
                best_value = value_comb
    return best_value

