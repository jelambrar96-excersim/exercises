public class SquareRoot {
    /**
     * Computes the square root of a non-negative integer using the Babylonian method.
     *
     * @param x the non-negative integer whose square root is to be computed
     * @return the integer part of the square root of `x`
     * @throws IllegalArgumentException if `x` is negative
     */
    public int squareRoot(int x) {
        /*
        * Compute the square root of a non-negative number using the Babylonian method.
        * The algorithm is taken from the Wikipedia article on "Methods of computing square roots":
        * https://en.wikipedia.org/wiki/Methods_of_computing_square_roots
        */
        if (x < 0) {
            throw new IllegalArgumentException("Cannot compute the square root of a negative number");
        }

        // Initialize the variables
        int c = 0, d = 1;

        // Compute the highest power of four that is less than or equal to `x`
        while (d <= x) {
            d *= 4;
        }
        d /= 4;

        // Perform the Babylonian method to compute the square root
        while (d != 0) {
            if (x >= (c + d)) {
                x -= (c + d);
                c = (c >> 1) + d;
            } else {
                c >>= 1;
            }
            d >>= 2;
        }

        return c;
    }
}
