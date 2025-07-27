import java.util.*;

public class SortedPermutations<T> implements Iterable<List<T>> {

    private final List<T> input;
    private final int k;

    public SortedPermutations(List<T> input, int k) {
        if (input == null || k < 0 || k > input.size()) {
            throw new IllegalArgumentException("Invalid input list or combination size");
        }
        this.input = new ArrayList<>(input); // make defensive copy
        this.k = k;
    }

    @Override
    public Iterator<List<T>> iterator() {
        return new SortedPermutationsIterator<>(input, k);
    }

    private static class SortedPermutationsIterator<T> implements Iterator<List<T>> {
        private final List<T> input;
        private final int[] indices;
        private final int n;
        private final int k;
        private boolean hasNext = true;

        public SortedPermutationsIterator(List<T> input, int k) {
            this.input = input;
            this.k = k;
            this.n = input.size();
            this.indices = new int[k];
            for (int i = 0; i < k; i++) {
                indices[i] = i;
            }
            if (k == 0 || k > n) {
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

            List<T> combination = new ArrayList<>(k);
            for (int i = 0; i < k; i++) {
                combination.add(input.get(indices[i]));
            }

            // Prepare next indices
            hasNext = advance();
            return combination;
        }

        private boolean advance() {
            for (int i = k - 1; i >= 0; i--) {
                if (indices[i] < n - k + i) {
                    indices[i]++;
                    for (int j = i + 1; j < k; j++) {
                        indices[j] = indices[j - 1] + 1;
                    }
                    return true;
                }
            }
            return false;
        }
    }

}
