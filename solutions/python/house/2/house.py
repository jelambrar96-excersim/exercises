VERSOS = [
    "the horse and the hound and the horn",
    "that belonged to",
    "the farmer sowing his corn",
    "that kept",
    "the rooster that crowed in the morn",
    "that woke",
    "the priest all shaven and shorn",
    "that married",
    "the man all tattered and torn",
    "that kissed",
    "the maiden all forlorn",
    "that milked",
    "the cow with the crumpled horn",
    "that tossed",
    "the dog",
    "that worried",
    "the cat",
    "that killed",
    "the rat",
    "that ate",
    "the malt",
    "that lay in",
    "the house that Jack built."
]


def recite(start_verse, end_verse):
    if start_verse <= 0 or start_verse > 12:
        raise ValueError("Invalid start verse")
    if end_verse <= 0 or end_verse > 12:
        raise ValueError("Invalid end verse")
    if end_verse < start_verse:
        raise ValueError("Invalid end verse must be higher to start verse")
    init_verse = -1 * start_verse * 2 + 1
    if start_verse == end_verse:
        return ["This is" + " " + " ".join(VERSOS[init_verse:])]
    return [ recite(i, i)[0] for i in range(start_verse, end_verse + 1) ]


VERSES = [
    "This is the house that Jack built.",
    "This is the malt that lay in the house that Jack built.",
    "This is the rat that ate the malt that lay in the house that Jack built.",
    "This is the cat that killed the rat that ate the malt that lay in the house that Jack built.",
    "This is the dog that worried the cat that killed the rat that ate the malt that lay in the house that Jack built.",
    "This is the cow with the crumpled horn that tossed the dog that worried the cat that killed the rat that ate the malt that lay in the house that Jack built.",
    "This is the maiden all forlorn that milked the cow with the crumpled horn that tossed the dog that worried the cat that killed the rat that ate the malt that lay in the house that Jack built.",
    "This is the man all tattered and torn that kissed the maiden all forlorn that milked the cow with the crumpled horn that tossed the dog that worried the cat that killed the rat that ate the malt that lay in the house that Jack built.",
    "This is the priest all shaven and shorn that married the man all tattered and torn that kissed the maiden all forlorn that milked the cow with the crumpled horn that tossed the dog that worried the cat that killed the rat that ate the malt that lay in the house that Jack built.",
    "This is the rooster that crowed in the morn that woke the priest all shaven and shorn that married the man all tattered and torn that kissed the maiden all forlorn that milked the cow with the crumpled horn that tossed the dog that worried the cat that killed the rat that ate the malt that lay in the house that Jack built.",
    "This is the farmer sowing his corn that kept the rooster that crowed in the morn that woke the priest all shaven and shorn that married the man all tattered and torn that kissed the maiden all forlorn that milked the cow with the crumpled horn that tossed the dog that worried the cat that killed the rat that ate the malt that lay in the house that Jack built.",
    "This is the horse and the hound and the horn that belonged to the farmer sowing his corn that kept the rooster that crowed in the morn that woke the priest all shaven and shorn that married the man all tattered and torn that kissed the maiden all forlorn that milked the cow with the crumpled horn that tossed the dog that worried the cat that killed the rat that ate the malt that lay in the house that Jack built.",
]


def recite2(start_verse, end_verse):
    return  VERSES[start_verse-1:end_verse]

