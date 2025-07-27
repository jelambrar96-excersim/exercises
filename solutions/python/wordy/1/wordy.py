DICT_OPERATIONS = {
    "plus": lambda x,y: x+y,
    "minus": lambda x,y: x-y,
    "multiplied": lambda x,y: x*y,
    "divided": lambda x,y: x/y,
}


def isnumber(string):
    try:
        __ = int(string)
    except ValueError:
        return False
    return True

def answer(question):
   
    question2 = question.replace("?", "").replace("What is", "").replace("by ", "").strip()
    if question2 == "":
        raise ValueError("syntax error")
    
    split_question = question2.split(" ")
    split_question = [item.strip() for item in split_question]
    
    len_split_question = len(split_question)
    if len_split_question == 1:
        return int(split_question[0])
    if len_split_question == 0:
        raise ValueError("syntax error")
    # if len_split_question % 2 == 0:
    #     raise ValueError("syntax error")
    
    next_value = split_question.pop(0)
    if next_value in DICT_OPERATIONS.keys():
        raise ValueError("syntax error")
    if not isnumber(next_value):
        raise ValueError("unknown operation")
    number = int(next_value)
    
    while len(split_question) > 0:
    
        operation = split_question.pop(0)
        if not operation in DICT_OPERATIONS.keys():
            if isnumber(operation):
                raise ValueError("syntax error")    
            raise ValueError("unknown operation") 
        if len(split_question) == 0:
            raise ValueError("syntax error")
        
        next_value = split_question.pop(0)
        if next_value in DICT_OPERATIONS.keys():
            raise ValueError("syntax error")
        next_number = int(next_value)
        
        number = DICT_OPERATIONS[operation](number, next_number)
    
    return number
    
    