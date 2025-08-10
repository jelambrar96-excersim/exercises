import java.util.List;
import java.util.SortedMap;

class PalindromeCalculator {

    public static boolean isPalindrome(long number) {
        String str = Long.toString(number);
        return str.equals(new StringBuilder(str).reverse().toString());
    }

    SortedMap<Long, List<List<Integer>>> getPalindromeProductsWithFactors(int minFactor, int maxFactor) {
        if (minFactor > maxFactor) throw new IllegalArgumentException("invalid input: min must be <= max");
        SortedMap<Long, List<List<Integer>>> palindromes = new java.util.TreeMap<>();
        for (int i = minFactor; i <= maxFactor; i++) {
            for (int j = i; j <= maxFactor; j++) {
                long product = Long.valueOf(i) * Long.valueOf(j);
                if (isPalindrome(product)) {
                    palindromes.computeIfAbsent(product, k -> new java.util.ArrayList<>())
                            .add(java.util.Arrays.asList(i, j));
                }
            }
        }
        return palindromes;   
    }
}
