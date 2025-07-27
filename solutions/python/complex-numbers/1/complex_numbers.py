import math

class ComplexNumber:

    def __init__(self, real, imaginary):
        self.real = real
        self.imaginary = imaginary
    
    def _force_complex_other(func):
        def wrapper(self, other):
            if isinstance(other, ComplexNumber) == False:
                other = ComplexNumber(other, 0)
            return func(self, other)
        return wrapper

    @_force_complex_other
    def __eq__(self, other):
        return other.real == self.real and other.imaginary == self.imaginary

    @_force_complex_other
    def __add__(self, other):
        return ComplexNumber(self.real + other.real, self.imaginary + other.imaginary)

    @_force_complex_other
    def __radd__(self, other):
        return other + self

    @_force_complex_other
    def __mul__(self, other):
        return ComplexNumber(
            self.real * other.real - self.imaginary * other.imaginary,
            self.real * other.imaginary + self.imaginary * other.real
        )

    @_force_complex_other
    def __rmul__(self, other):
        return other * self

    @_force_complex_other
    def __sub__(self, other):
        return ComplexNumber(self.real - other.real, self.imaginary - other.imaginary)

    @_force_complex_other
    def __rsub__(self, other):
        return other - self

    @_force_complex_other
    def __truediv__(self, other):
        div = other.real ** 2 + other.imaginary ** 2
        return ComplexNumber(
            (self.real * other.real + self.imaginary * other.imaginary) / div,
            (self.imaginary * other.real - self.real * other.imaginary) / div
        )

    @_force_complex_other
    def __rtruediv__(self, other):
        return other / self

    def __abs__(self):
        return (self.real ** 2 + self.imaginary ** 2) ** 0.5

    def conjugate(self):
        return ComplexNumber(self.real, -1 * self.imaginary)

    def exp(self):
        return ComplexNumber(
            math.exp(self.real) * math.cos(self.imaginary),
            math.exp(self.real) * math.sin(self.imaginary)
        )
