from itertools import islice 

L_STUDENTS = [
    'Alice',  'Bob',     'Charlie',   'David', 
    'Eve',    'Fred',    'Ginny',     'Harriet', 
    'Ileana', 'Joseph',  'Kincaid',   'Larry'
]

FLOWER_DICT = {
    "C": "Clover",
    "G": "Grass",
    "R": "Radishes",
    "V": "Violets"
}

def batched(iterable, n, *, strict=False):
    # batched('ABCDEFG', 3) → ABC DEF G
    if n < 1:
        raise ValueError('n must be at least one')
    iterator = iter(iterable)
    while batch := tuple(islice(iterator, n)):
        if strict and len(batch) != n:
            raise ValueError('batched(): incomplete batch')
        yield batch

class Garden:
    def __init__(self, diagram, students=None):
        self.students = sorted(students) if students is not None else L_STUDENTS
        g = [ ["".join(b) for b in batched(it, 2)] for it in diagram.split("\n")]
        self.garden = [ "".join(item) for item in zip(*g)]

    def plants(self, student):
        pl = self.garden[self.students.index(student)]
        return [FLOWER_DICT[item] for item in pl]

