import java.lang.IllegalArgumentException;


public class Hamming {

    private int hammingDistance;

    public Hamming(String leftStrand, String rightStrand) {
        char [] leftCharStrand = leftStrand.toCharArray();
        char [] rightCharStrand = rightStrand.toCharArray();
        if (leftCharStrand.length != rightCharStrand.length) {
            throw new IllegalArgumentException("strands must be of equal length");
        }
        this.hammingDistance = 0;
        for (int i=0; i<leftCharStrand.length; ++i) {
            if (leftCharStrand[i] != rightCharStrand[i]) this.hammingDistance += 1;
        }
    }

    public int getHammingDistance() {
        return this.hammingDistance;
    }
}
