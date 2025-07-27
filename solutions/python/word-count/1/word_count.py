from collections import Counter

SIMBOLS = [":", "!", "?", "\t", "\n", ".", ",", ";", "&", "@", "$", "%", "^", "_"]

def count_words(sentence):
    lower_sentence = sentence.lower()
    for s in SIMBOLS:
        lower_sentence = lower_sentence.replace(s, " ")
    word_list = [ word.strip().strip("'") for word in lower_sentence.split(" ") ]
    word_list = [ item for item in word_list if item != "" ]
    word_counter = Counter(word_list)
    return dict(word_counter)