import java.math.BigInteger;
import java.lang.IllegalArgumentException;

class Grains {

    BigInteger grainsOnSquare(final int square) {
        if (square < 1 || square > 64) {
            throw new IllegalArgumentException("square must be between 1 and 64");
        }
        return BigInteger.valueOf((long)Math.pow(2, square - 1));
    }

    BigInteger grainsOnBoard() {
        return BigInteger.valueOf((long)Math.pow(2, 64) - 1);
    }

}
