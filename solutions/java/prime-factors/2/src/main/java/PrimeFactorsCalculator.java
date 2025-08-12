import java.lang.Math;
import java.util.ArrayList;
import java.util.List;

class PrimeFactorsCalculator {

    List<Long> calculatePrimeFactorsOf(long number) {
        
        int numberOfPrimes = (int)Math.ceil(Math.log(number)/number);
        ArrayList<Long> factors = new ArrayList<Long>(numberOfPrimes);

        while (number % 2 == 0) {
            factors.add((long)2);
            number /= 2;
        }

        long currentFactor = 3;
        while (number != 1) {
            while(number % currentFactor == 0) {
                factors.add(currentFactor);
                number /= currentFactor;
            }
            currentFactor += 2;
        }
        return factors;
    }

}