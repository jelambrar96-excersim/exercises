class BufferFullException(BufferError):
    """Exception raised when CircularBuffer is full.

    message: explanation of the error.

    """
    def __init__(self, message):
        self.message = message


class BufferEmptyException(BufferError):
    """Exception raised when CircularBuffer is empty.

    message: explanation of the error.

    """
    def __init__(self, message):
        self.message = message


class CircularBuffer:
    def __init__(self, capacity):
        self._list = [None] * capacity
        self.capacity = capacity
        self._ind = 0
        self._rind = 0
        self._num_elements = 0

    def read(self):
        if self.isempty():
            raise BufferEmptyException("Circular buffer is empty")
        e = self._list[self._rind]
        self._list[self._rind] = None
        self._rind += 1
        self._rind %= self.capacity
        self._num_elements -= 1
        return e

    def write(self, data):
        if self.isfull():
            raise BufferFullException("Circular buffer is full")
        self._list[self._ind] = data
        self._ind += 1
        self._ind %= self.capacity
        self._num_elements += 1

    def overwrite(self, data):
        if not self.isfull():
            self.write(data)
            return
        self._list[self._ind] = data
        self._ind += 1
        self._ind %= self.capacity
        self._rind += 1
        self._rind %= self.capacity
    
    def clear(self):
        self._ind = 0
        self._rind = 0
        self._list = [None] * self.capacity
        self._num_elements = 0

    def isfull(self):
        return self.capacity <= self._num_elements

    def isempty(self):
        return self._num_elements == 0