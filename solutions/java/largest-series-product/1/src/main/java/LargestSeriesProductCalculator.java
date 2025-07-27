
class LargestSeriesProductCalculator {
    
    int [] digits;

    LargestSeriesProductCalculator(String inputNumber) {
        this.digits =  new int[inputNumber.length()];
        char [] charArrray = inputNumber.toCharArray();
        for (int i = 0, n = charArrray.length; i < n; ++i) {
            char c = charArrray[i];
            if ( c < '0' || c > '9') throw new IllegalArgumentException("String to search may only contain digits.");
            this.digits[i] = Character.getNumericValue(c);
        }
    }

    long calculateLargestProductForSeriesLength(int numberOfDigits) {
        if (numberOfDigits < 0) throw new IllegalArgumentException("Series length must be non-negative.");
        if (numberOfDigits > digits.length) throw new IllegalArgumentException("Series length must be less than or equal to the length of the string to search.");

        long maxP = 0;
        for (int i = 0, n = digits.length - numberOfDigits + 1; i < n; ++i) {
            long p = 1;
            for (int j = i, m = i + numberOfDigits; j < m; ++j) p *= (long)digits[j];
            if (p > maxP) maxP = p;
        }
        return maxP;
    }
}
