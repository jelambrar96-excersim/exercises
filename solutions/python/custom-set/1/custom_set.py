class CustomSet:
    def __init__(self, elements=[]):
        self.elements = []
        for item in elements:
            if item in self.elements:
                continue
            self.elements.append(item)

    def isempty(self):
        return len(self.elements) == 0

    def __contains__(self, element):
        return element in self.elements

    def issubset(self, other):
        if self.isempty():
            return True
        return all((item in other) for  item in self.elements)

    def isdisjoint(self, other):
        return self.intersection(other).isempty()

    def __eq__(self, other):
        return self.issubset(other) and other.issubset(self)

    def add(self, element):
        if not element in self.elements:
            self.elements.append(element)

    def intersection(self, other):
        return CustomSet(item for item in self.elements if item in other)


    def __sub__(self, other):
        return CustomSet(item for item in self.elements if not item in other)


    def __add__(self, other):
        cs = CustomSet(item for item in other.elements)
        for item in self.elements:
            if item in cs:
                continue
            cs.add(item)
        return cs

