class School:
    def __init__(self):
        self._students = {}
        self._added = []

    def add_student(self, name, grade):
        flag = name in self._students.keys()
        self._added.append( not flag)
        if flag:
            return False
        self._students[name] = grade

    def roster(self):
        grades = sorted(list(set(self._students.values())))
        out = []
        for g in grades:
            out.extend(self.grade(g))
        return out

    def grade(self, grade_number):
        return sorted([ key for key, value in self._students.items() if value == grade_number ])

    def added(self):
        return self._added
