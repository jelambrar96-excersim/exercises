def decode(string):
    number_str = ""
    out = ""
    counter = 0
    for s in string:
        if s.isdigit():
            number_str = number_str + s
        else:
            counter = int(number_str) if number_str != "" else 1
            number_str = ""
            out = out + s * counter
    return out
        


def encode(string):
    if string == "":
        return ""
    out = ""
    last_s = string[0]
    counter_s = 0
    for s in string:
        if s == last_s:
            counter_s += 1
        else:
            str_counter = str(counter_s) if counter_s > 1 else ""
            out = out + str_counter + last_s
            last_s = s
            counter_s = 1
    str_counter = str(counter_s) if counter_s > 1 else ""
    out = out + str_counter + last_s 
    return out 
            
