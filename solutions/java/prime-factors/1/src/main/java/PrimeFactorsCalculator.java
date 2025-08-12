import java.lang.Math;
import java.util.ArrayList;
import java.util.List;

class PrimeFactorsCalculator {

    private static boolean divisible(long number, List<Long> numbers) {
        for (Long n: numbers) {
            if (number % n == 0) return true;
        }
        return false;
    }

    List<Long> calculatePrimeFactorsOf(long number) {
        
        int numberOfPrimes = (int)Math.ceil(Math.log(number)/number);
        ArrayList<Long> primes = new ArrayList<Long>(numberOfPrimes);
        ArrayList<Long> factors = new ArrayList<Long>(numberOfPrimes);

        while (number % 2 == 0) {
            factors.add((long)2);
            number /= 2;
        }

        long currentFactor = 3;
        while (number != 1) {
            if (divisible(currentFactor, primes)) {
                currentFactor += 2;
                continue;
            }
            primes.add(currentFactor);
            while(number % currentFactor == 0) {
                factors.add(currentFactor);
                number /= currentFactor;
            }
            currentFactor += 2;
        }
        return factors;
    }

}