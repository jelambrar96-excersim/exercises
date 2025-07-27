from math import gcd, pow

class Rational:
    def __init__(self, numer, denom):
        if denom == 0:
            raise ZeroDivisionError()
        self.numer = abs(numer)
        self.denom = abs(denom)
        if (numer * denom) < 0:
            self.numer  *= -1
        self.__simplify()

    def __eq__(self, other):
        return self.numer == other.numer and self.denom == other.denom

    def __repr__(self):
        return f'{self.numer}/{self.denom}'

    def __simplify(self):
        if self.numer == 0:
            self.numer, self.denom = 0, 1
            return
        gcd_num_den = gcd(self.numer, self.denom)
        self.numer //= gcd_num_den
        self.denom //= gcd_num_den

    def __add__(self, other):
        num = self.numer * other.denom + self.denom * other.numer
        den = self.denom * other.denom
        return Rational(num, den)
    
    def __radd__(self, other):
        return other + self

    def __sub__(self, other):
        num = self.numer * other.denom - self.denom * other.numer
        den = self.denom * other.denom
        return Rational(num, den)

    def __radd__(self, other):
        return other - self

    def __mul__(self, other):
        num = self.numer * other.numer
        den = self.denom * other.denom
        return Rational(num, den)

    def __rmul__(self, other):
        return other * self

    def __truediv__(self, other):
        if other.numer == 0:
            raise ZeroDivisionError()
        num = self.numer * other.denom
        den = self.denom * other.numer
        return Rational(num, den) 

    def __rtruediv__(self, other):
        return other / self

    def __abs__(self):
        return Rational(abs(self.numer), abs(self.denom))

    def __pow__(self, power):
        if power == 0:
            return Rational(1, 1)
        if self.numer == 0:
            return Rational(0, 1)
        num, den = (self.numer, self.denom) if power > 0 else (self.denom, self.numer)
        power = abs(power)
        den = int(pow(den, power))
        num = int(pow(num, power))
        return Rational(num, den)


    def __rpow__(self, base):
        return pow(base, self.numer/self.denom)

