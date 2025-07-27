import java.lang.Integer;
import java.lang.IllegalArgumentException;
import java.lang.Math;

class PrimeCalculator {

    int nth(int nth) {
        if (nth < 1) throw new IllegalArgumentException();
        if (nth == 1) return 2;
        
        nth--;
        int []arrPrimes = new int[nth];

        int primeCounter = 0;
        int currentNumber = 1;

        while (primeCounter < nth) {

            currentNumber += 2;
            boolean isPrime = true;
            for (int i = 0; i < primeCounter; ++i) {
                int p = arrPrimes[i];
                if (currentNumber % p == 0) {
                    isPrime = false;
                    break;
                }
                if (currentNumber / p < 3) break;
            }

            if (isPrime) {
                arrPrimes[primeCounter] = currentNumber;
                primeCounter++;
            }
        }
        return currentNumber;
    }

}
