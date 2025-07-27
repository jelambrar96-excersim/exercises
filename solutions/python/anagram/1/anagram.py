
def is_anagram(word, candidate):
    word, candidate = word.lower(), candidate.lower()
    if word == candidate:
        return False
    if len(word) != len(candidate):
        return False
    new_word, new_candidate = tuple(sorted(word)), tuple(sorted(candidate))
    return all( nw == nc for nw, nc in zip(new_word, new_candidate))


def find_anagrams(word, candidates):
    return [ item for item in candidates if is_anagram(word, item)]
