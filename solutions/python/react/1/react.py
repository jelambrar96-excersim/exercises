class Cell:

    def __init__(self, initial_value):
        self._value = initial_value
        self._listeners = set()

    def add_listener(self, cell):
        self._listeners.add(cell)

    def notify_listeners(self, changed):
        for listener in self._listeners:
            listener.recompute(changed)

    @property
    def value(self):
        return self._value


class InputCell(Cell):

    @Cell.value.setter
    def value(self, val):
        if val == self._value:
            return
        self._value = val
        changed = {}
        self.notify_listeners(changed)
        for cell, original_value in changed.items():
            if cell.value != original_value:
                cell.call_callbacks()


class ComputeCell(Cell):

    def __init__(self, inputs, compute_function):
        self._inputs = inputs
        self._compute_function = compute_function
        self._callbacks = set()
        for i in inputs:
            i.add_listener(self)
        super().__init__(self.compute())

    def add_callback(self, callback):
        self._callbacks.add(callback)

    def remove_callback(self, callback):
        self._callbacks.discard(callback)

    def compute(self):
        return self._compute_function([i.value for i in self._inputs])

    def recompute(self, changed):
        val = self.compute()
        if val != self.value:
            changed.setdefault(self, self.value)
            self._value = val
            self.notify_listeners(changed)

    def call_callbacks(self):
        for callback in self._callbacks:
            callback(self.value)
