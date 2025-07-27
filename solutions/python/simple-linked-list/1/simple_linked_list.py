class EmptyListException(Exception):
    """Exception raised when the linked list is empty.

    message: explanation of the error.

    """
    def __init__(self, message):
        self.message = message


class Node:

    def __init__(self, value):
        self.value_node = value
        self.next_node = None

    def value(self):
        return self.value_node

    def next(self):
        return self.next_node


class LinkedList:

    def __init__(self, values=None):
        self._head = None
        if values is not None:
            for v in values:
                self.push(value=v)                
                print(self._head is None)


    def __iter__(self):
        current_node = self._head
        while True:
            if current_node is None:
                break
            value = current_node.value()
            current_node = current_node.next()
            yield value


    def __len__(self):
        if self._head is None:
            return 0
        counter = 0
        current_node = self._head
        while True:
            current_node = current_node.next()
            counter += 1
            if current_node is None:
                break
        return counter


    def head(self):
        if self._head is None:
            raise EmptyListException("The list is empty.")
        return self._head


    def push(self, value):
        new_node = Node(value)
        if self._head is not None:
            new_node.next_node = self._head
        self._head = new_node


    def pop(self):
        if self._head is None:
            raise EmptyListException("The list is empty.")
        temp_node = self._head
        self._head = temp_node.next()
        return temp_node.value()
        

    def reversed(self):
        reversed_linked_list = LinkedList()
        for v in self:
            reversed_linked_list.push(v)
        return reversed_linked_list
 