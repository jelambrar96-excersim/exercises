from collections import Counter
from itertools import combinations
from functools import lru_cache, reduce
from math import gcd


DISCOUNT_TABLE= [0, 0, 5, 10, 20, 25]


@lru_cache(maxsize=1024)
def getbest(new_basket, total, max_group_size=None):

    len_basket = len(new_basket)
    if len_basket == 0:
        return total
    
    categories = len(set(new_basket)) 
    base_total = len_basket * 800 + total

    if categories == 1:
        return base_total
    
    if categories == len_basket:
        return total + len_basket * 8 * ( 100 - DISCOUNT_TABLE[len_basket] )
    

    cnt = Counter(new_basket)
    gcd_cnt = reduce(gcd, cnt.values())
    if gcd_cnt > 1:
        minimal = Counter({k: v // gcd_cnt for k, v in cnt.items()})
        minimal_books = tuple(sorted(minimal.elements()))
        return total + getbest(minimal_books, 0, None) * gcd_cnt


    initial_category = categories if max_group_size is None else min(max_group_size, categories)

    for group_size in range(initial_category, 1, -1):
        for combs in combinations(new_basket, group_size):
            
            if group_size > len(set(combs)):
                continue

            total_group = 8 * group_size * ( 100 - DISCOUNT_TABLE[group_size])

            new_basket_rec = list(new_basket)
            for item in combs:
                new_basket_rec.remove(item)
            new_basket_rec = tuple(new_basket_rec)
            temp_total = getbest(new_basket_rec, total + total_group, group_size)    

            if temp_total < base_total:
                base_total = temp_total
                            

    return base_total



def total(basket):
    basket = tuple(sorted(basket))
    return getbest(basket, 0)
