class SgfTree:
    def __init__(self, properties=None, children=None):
        self.properties = properties or {}
        self.children = children or []

    def __eq__(self, other):
        if not isinstance(other, SgfTree):
            return False
        for key, value in self.properties.items():
            if key not in other.properties:
                return False
            if other.properties[key] != value:
                return False
        for key in other.properties.keys():
            if key not in self.properties:
                return False
        if len(self.children) != len(other.children):
            return False
        for child, other_child in zip(self.children, other.children):
            if child != other_child:
                return False
        return True

    def __ne__(self, other):
        return not self == other


def parse(input):
    pos = 1

    def get_property():
        nonlocal pos
        ind = pos
        while input[ind].isupper():
            ind += 1
        if input[ind].islower():
            raise ValueError('property must be in uppercase')
        if input[ind] != '[':
            raise ValueError('properties without delimiter')
        key = input[pos:ind]
        prop = {key: []}
        pos = ind
        while input[pos] == '[':
            pos = ind = pos + 1
            w = []
            while input[ind] != ']':
                if input[ind] == '\t':
                    w.append(' ')
                elif input[ind:ind + 2] in ('\\]', '\\\\'):
                    w.append(input[ind + 1])
                    ind += 1
                elif input[ind:ind + 2] == '\\\n':
                    ind += 1
                elif input[ind] != '\\':
                    w.append(input[ind])
                ind += 1
            prop[key].append(''.join(w))
            pos = ind + 1
        return prop
    
    def get_node():
        nonlocal pos
        properties = {}
        children = []
        while input[pos] == '(':
            pos += 1
        if input[pos] == ')':
            raise ValueError('tree with no nodes')
        pos += 1
        while input[pos] not in ('(', ')', ';'):
            properties.update(get_property())
        while pos < len(input) and input[pos] != ')':
            children.append(get_node())
        pos += 1
        return SgfTree(properties, children)  
        
    if not input.startswith('('):
        raise ValueError('tree missing')
    return get_node()
