import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.List;

class BookStore {

    final private static double[] DISCOUNT_TABLE = {0.0, 0.0, 5.0, 10.0, 20.0, 25.0}; 
    final private static double BOOK_COST = 8.00;

    Map<List<Integer>, Double> cacheBaskterCost = new HashMap<List<Integer>, Double>();


    double calculateBasketCost(List<Integer> books) {
        
        // sort list to
        List<Integer> sortedBooks = books.stream().sorted().toList();

        int sizeBooks = sortedBooks.size();
        if (sizeBooks == 0) { return 0; }

        if (cacheBaskterCost.containsKey(sortedBooks)) { return cacheBaskterCost.get(sortedBooks); }
        double minCost = sizeBooks * BOOK_COST;

        // categories
        List<Integer> categories = sortedBooks.stream().distinct().toList();
        int numCategories = categories.size();
        if (numCategories == 1) {
            return minCost;
        }

        if (numCategories == sizeBooks) {
            return minCost * (100 - DISCOUNT_TABLE[numCategories]) / 100;
        }

        // histogram
        Map<Integer, Long> bookHistogramMap = sortedBooks.stream()
            .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        // compute gcd
        long gcd = bookHistogramMap.values().stream()
            .reduce(bookHistogramMap.values().stream().toList().get(0), BookStore::gcd)
            .longValue();
        if (gcd > 1) {
            List<Integer> minimalList = new ArrayList<Integer>();
            for (Map.Entry<Integer, Long> entry: bookHistogramMap.entrySet()) {
                for (long i = 0, n = entry.getValue() / gcd; i < n; ++i) {
                    minimalList.add(entry.getKey());
                }
            }
            return calculateBasketCost(minimalList) * gcd;
        }

        for (int i = 1; i <= numCategories; ++i) {
            double costGroup = BOOK_COST * i * (100 - DISCOUNT_TABLE[i]) / 100;
            Combinations<Integer> combinations = new Combinations<Integer>(categories, i);
            for (List<Integer> comb: combinations) {
                ArrayList<Integer> newBookList = new ArrayList<>(sortedBooks);
                for(Integer cat: comb) {
                    newBookList.remove(cat);
                }
                double tempCost = costGroup + calculateBasketCost(newBookList);
                if (tempCost < minCost) {
                    minCost = tempCost;
                }
            }
        }

        cacheBaskterCost.put(sortedBooks, minCost);
        return minCost;
    }

    public static long gcd(long a, long b) {
        while (b != 0) {
            long temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

}