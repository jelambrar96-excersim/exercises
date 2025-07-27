TABLE = [
    "no", "one", "two", "three", "four",
    "five", "six", "seven", "eight", "nine", "ten"
]

TEMPLATE = """{} hanging on the wall,
{} hanging on the wall,
And if one green bottle should accidentally fall,
There'll be {} hanging on the wall."""

def bottle_str(num):
    template = "{} green bottle{}"
    return template.format(TABLE[num], "s" if num != 1 else "")

def recite(start, take=1):
    out = []
    num_bottles = start
    for i in range(take):
        item1 = bottle_str(num_bottles - i).capitalize()
        item2 = bottle_str(num_bottles - 1 - i)
        verses = TEMPLATE.format(item1, item1, item2)
        out.append(verses)
    return "\n\n".join(out).split("\n")
