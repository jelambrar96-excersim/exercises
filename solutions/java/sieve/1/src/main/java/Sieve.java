import java.lang.Integer;
import java.util.ArrayList;
import java.util.List;

class Sieve {

    List<Integer> listPrimes;

    Sieve(int maxPrime) {

        int aproxSize = (int)Math.ceil( maxPrime / Math.log(maxPrime));
        this.listPrimes = new ArrayList<Integer>(aproxSize);
    
        if (maxPrime >= 2) this.listPrimes.add(2);
        for (int i = 3; i <= maxPrime; i += 2) {
            boolean isPrime = true;
            for (int j: this.listPrimes) {
                if (i % j == 0) {
                    isPrime = false;
                    break;
                }
                if (i / j < 3) break;
            }
            if (isPrime) this.listPrimes.add(i);
        }

    }

    List<Integer> getPrimes() {
        return this.listPrimes;
    }
}
