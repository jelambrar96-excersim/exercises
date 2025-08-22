import java.util.*;

/**
 * Generic class to generate all r-combinations from a collection.
 * Each iteration returns a List<T> representing one combination.
 */
public class Combinations<T> implements Iterable<List<T>> {

    private final List<T> items;
    private final int r;

    public Combinations(Collection<T> collection, int r) {
        if (r < 0 || r > collection.size()) {
            throw new IllegalArgumentException("Invalid r: " + r);
        }
        this.items = new ArrayList<>(collection);
        this.r = r;
    }

    @Override
    public Iterator<List<T>> iterator() {
        return new CombinationIterator();
    }

    private class CombinationIterator implements Iterator<List<T>> {
        private final int n = items.size();
        private final int[] indices;
        private boolean hasNext = true;

        CombinationIterator() {
            indices = new int[r];
            for (int i = 0; i < r; i++) {
                indices[i] = i;
            }
            if (r == 0) {
                hasNext = false;
            }
        }

        @Override
        public boolean hasNext() {
            return hasNext;
        }

        @Override
        public List<T> next() {
            if (!hasNext) throw new NoSuchElementException();

            // Build current combination
            List<T> combination = new ArrayList<>(r);
            for (int i : indices) {
                combination.add(items.get(i));
            }

            // Advance indices for next combination
            hasNext = advance();

            return combination;
        }

        private boolean advance() {
            for (int i = r - 1; i >= 0; i--) {
                if (indices[i] < n - r + i) {
                    indices[i]++;
                    for (int j = i + 1; j < r; j++) {
                        indices[j] = indices[j - 1] + 1;
                    }
                    return true;
                }
            }
            return false;
        }
    }

    // Example usage
    // public static void main(String[] args) {
    //     List<String> letters = Arrays.asList("A", "B", "C", "D");

    //     Combinations<String> combinations = new Combinations<>(letters, 2);
    //     for (List<String> c : combinations) {
    //         System.out.println(c);
    //     }
    //     // Output:
    //     // [A, B]
    //     // [A, C]
    //     // [A, D]
    //     // [B, C]
    //     // [B, D]
    //     // [C, D]
    // }
}
