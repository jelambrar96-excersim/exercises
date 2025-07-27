DICTORY = {
    "1": "one",
    "2": "two",
    "3": "three",
    "4": "four",
    "5": "five",
    "6": "six",
    "7": "seven",
    "8": "eight",
    "9": "nine",
    "10": "ten",
    "11": "eleven",
    "12": "twelve",
    "20": "twenty",
    "30": "thirty",
    "40": "forty",
    "50": "fifty",
    "60": "sixty",
    "70": "seventy",
    "80": "eighty",
    "90": "ninety"
}

SUFIX = ["", "thousand", "million", "billion", "trillon"]

def say(number):
    if number < 0:
        raise ValueError("input out of range")
    if number >= 1e12:
        raise ValueError("input out of range")
    if number == 0:
        return "zero"
    number_str = str(number)
    if number_str in DICTORY.keys():
        return DICTORY[number_str]
    if number < 20:
        return DICTORY[number_str[1]] + "teen"

    if number < 100:
        out = say((number // 10) * 10)
        if number % 10 == 0:
            return out
        return out + "-" +  say(number % 10)

    if number < 1000:
        out = DICTORY[number_str[0]] + " hundred"
        if number % 100 == 0:
            return out
        return out + " " + say(number % 100)

    len_number = len(number_str)
    splited_str = [number_str[::-1][i:min(i+3, len_number)][::-1] for i in range(0,len_number,3)]
    zipped = list(zip(splited_str, SUFIX[:len(splited_str)]))[::-1]

    out = []
    for i, (item, sfx) in enumerate(zipped):
        temp_number = int(item)
        if temp_number == 0:
            continue
        out.append(say(temp_number))
        if sfx != "":
            out.append(sfx)

    return " ".join(out)
    