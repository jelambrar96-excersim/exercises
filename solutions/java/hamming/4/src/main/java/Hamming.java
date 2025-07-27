import java.lang.IllegalArgumentException;


public class Hamming {

    private int hammingDistance;

    public Hamming(String leftStrand, String rightStrand) {
        if (leftStrand.length() != rightStrand.length()) {
            throw new IllegalArgumentException("strands must be of equal length");
        }
        this.hammingDistance = 0;
        for (int i = 0, n = leftStrand.length(); i < n; ++i) {
            if (leftStrand.charAt(i) != rightStrand.charAt(i)) this.hammingDistance += 1;
        }
    }

    public int getHammingDistance() {
        return this.hammingDistance;
    }

}
