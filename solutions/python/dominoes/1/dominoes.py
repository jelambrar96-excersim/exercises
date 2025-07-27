from itertools import permutations


def can_chain(dominoes):

    len_dominoes = len(dominoes)
    if len_dominoes == 0:
        return []
    
    if len_dominoes == 1:
        if dominoes[0][0] != dominoes[0][1]:
            return None
        return dominoes

    for per_dominoes in permutations(dominoes):
        
        chain_dominoes = list(per_dominoes)
        head, tail = chain_dominoes[0], chain_dominoes[-1]
        if head[0] == tail[0]:
            chain_dominoes[-1] = tail[1], tail[0]
        elif head[1] == tail[1]:
            chain_dominoes[0] = head[1], head[0]
        elif head[0] == tail[1]:
            pass
        elif head[1] == tail[0]:
            chain_dominoes[-1] = tail[1], tail[0]
            chain_dominoes[0] = head[1], head[0]
        else:
            continue

        flag = False
        for i in range(1, len_dominoes - 1):
            d0 = chain_dominoes[i]
            if d0[0] in chain_dominoes[i - 1] and d0[1] in chain_dominoes[i + 1]:
                continue
            if d0[0] in chain_dominoes[i + 1] and d0[1] in chain_dominoes[i - 1]:
                chain_dominoes[i] = d0[1], d0[0]
            else:
                flag = True
                break
        
        if not flag:
            return chain_dominoes

    return None
