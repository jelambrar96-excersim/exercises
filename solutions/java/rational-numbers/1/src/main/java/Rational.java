import java.util.Objects;

class Rational {

    private int num, den;

    Rational(int numerator, int denominator) {
        if (denominator == 0) {
            throw new IllegalArgumentException();
        }
        if (numerator == 0) {
            this.num = 0; 
            this.den = 1;
            return;
        }
        boolean isNegative = numerator > 0 ^ denominator > 0;
        numerator   = numerator   > 0 ? numerator   : -1 * numerator;
        denominator = denominator > 0 ? denominator : -1 * denominator;

        int ratio = gdc(numerator, denominator);
        this.num = numerator / ratio * (isNegative ? -1 : 1);
        this.den = denominator / ratio;
    }

    int getNumerator() {
        return this.num;
    }

    int getDenominator() {
        return this.den;
    }

    Rational add(Rational other) {
        return new Rational(this.num * other.den + this.den * other.num, this.den * other.den);
    }

    Rational subtract(Rational other) {
        return new Rational(this.num * other.den - this.den * other.num, this.den * other.den);
    }

    Rational multiply(Rational other) {
        return new Rational(this.num * other.num, this.den * other.den);
    }

    Rational divide(Rational other) {
        if (other.num == 0) { throw new IllegalArgumentException(); }
        return new Rational(this.num * other.den, this.den * other.num);
    }

    Rational abs() {
        return new Rational(this.num > 0 ? this.num : -1 * this.num, den);
    }

    Rational pow(int power) {
        int n = 1, d = 1;
        int abspower = power > 0 ? power : -1 * power;
        for (int i = 0; i < abspower; ++i) {
            n *= this.num;
            d *= this.den;
        }
        if (power < 0) { return new Rational(d, n); }
        return new Rational(n, d);
    }

    double exp(double exponent) {
        return Math.pow(exponent, (double)this.num / (double)this.den);
    }

    @Override
    public String toString() {
        return String.format("%d/%d", this.getNumerator(), this.getDenominator());
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Rational other) {
            return this.getNumerator() == other.getNumerator()
                    && this.getDenominator() == other.getDenominator();
        }

        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getNumerator(), this.getDenominator());
    }

    private int gdc(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
}
