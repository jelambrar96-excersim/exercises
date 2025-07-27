import java.math.BigInteger;

class Grains {

    BigInteger grainsOnSquare(final int square) {
        return BigInteger.valueOf((long)Math.pow(2, square - 1));
    }

    BigInteger grainsOnBoard() {
        return BigInteger.valueOf((long)Math.pow(2, 64) - 1);
    }

}
