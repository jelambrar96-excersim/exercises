VERSES = (
    "a Partridge in a Pear Tree.",
    "two Turtle Doves, and",
    "three French Hens,",
    "four Calling Birds,",
    "five Gold Rings,",
    "six Geese-a-Laying,",
    "seven Swans-a-Swimming,",
    "eight Maids-a-Milking,",
    "nine Ladies Dancing,",
    "ten Lords-a-Leaping,",
    "eleven Pipers Piping,",
    "twelve Drummers Drumming,"
)

DAYS = (
    'first', 'second', 'third', 'fourth', 'fifth', 'sixth',
    'seventh', 'eighth', 'ninth', 'tenth', 'eleventh', 'twelfth'
)

def recite_single_verse(verse_number: int) -> str:
    day = DAYS[verse_number]
    gifts = " ".join(VERSES[verse_number::-1])
    return f"On the {day} day of Christmas my true love gave to me: {gifts}"

def recite(start_verse, end_verse):
    return list(map(recite_single_verse, range(start_verse - 1, end_verse)))


if __name__  == '__main__':
    print(*recite(3,3), sep='\n')
