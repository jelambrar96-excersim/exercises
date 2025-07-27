class Node:
    def __init__(self, value, succeeding=None, previous=None):
        self._value = value
        self.succeeding = succeeding
        self.previous = previous

    @property
    def value(self):
        return self._value


class LinkedList:
    def __init__(self):
        self._head = None
        self._tail = None
        self.length = 0


    def __iter__(self):
        current_node = self._head
        while current_node is not None:
            yield current_node.value
            current_node = current_node.succeeding


    def __len__(self):
        return self.length


    def delete(self, value):
        if self._head is None:
            raise ValueError("Value not found")
        current_node = self._head
        found = False
        while current_node is not None:
            if current_node.value != value:
                current_node = current_node.succeeding
                continue
            found = True

            prev = current_node.previous
            succ = current_node.succeeding

            if prev is None and succ is None:
                self._head = None
                self._tail  = None
                self.length = 0 
                del current_node
                break
            
            if succ is None: 
                self._tail = prev
                self.length -= 1 
                del current_node
                break

            if prev is None: 
                self._head = succ
                self.length -= 1
                del current_node 
                current_node = succ
                continue 

            prev.succeeding = succ
            succ.previous = prev
            del current_node
            self.length -= 1
            break

        if not found:
            raise ValueError("Value not found")


    def pop(self,):
        if self.length == 0:
            raise IndexError("List is empty")
        if self.length == 1:
            head = self._head
            tail = self._tail
            head_value = self._head.value
            self._head = None
            self._tail = None
            del head
            del tail
            self.length = 0
            return head_value
        tail = self._tail
        previous = tail.previous
        value = tail.value
        previous.succeeding = None
        self._tail = previous
        del tail
        self.length -= 1 
        return value


    def push(self, value):
        new_node = Node(value)
        if self._tail is None:
            self._head = new_node
            self._tail = new_node
            self.length = 1
            return
        new_node = Node(value)
        new_node.previous = self._tail
        self._tail.succeeding = new_node
        self._tail = new_node
        self.length += 1


    def shift(self):
        if self.length == 0:
            raise IndexError("List is empty")
        if self.length == 1:
            head = self._head
            tail = self._tail
            head_value = self._head.value
            self._head = None
            self._tail = None
            del head
            del tail
            self.length = 0
            return head_value
        head = self._head
        head_successing = head.succeeding
        value = head.value
        head_successing.previous = None
        self._head = head_successing
        del head
        self.length -= 1
        return value


    def unshift(self, value):
        new_node = Node(value)
        if self._tail is None:
            self._head = new_node
            self._tail = new_node
            self.length = 1
            return
        new_node = Node(value)
        new_node.succeeding = self._head
        self._head.previous = new_node
        self._head = new_node
        self.length += 1
    