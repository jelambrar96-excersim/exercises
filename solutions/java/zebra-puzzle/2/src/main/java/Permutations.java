import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class Permutations<T> implements Iterable<List<T>> {
    private List<T> items;
    private int k;

    public Permutations(List<T> items) {
        this.items = items;
        this.k = items.size();
    }

    public Permutations(List<T> items, int k) {
        this.items = items;
        this.k = k;
    }

    @Override
    public Iterator<List<T>> iterator() {
        return new PermutationIterator<>(items, k);
    }

    private static class PermutationIterator<T> implements Iterator<List<T>> {

        private final int k;
        private final List<T> items;
        private List<T> currentPerm;
        private boolean hasNext;
        private boolean [] used;
        private int deep;
        private int [] indexes;

        public PermutationIterator(List<T> items, int k) {
            this.k = k;
            this.items = items;
            this.hasNext = true;
            this.currentPerm = null;
            this.deep = 0;
            this.used = new boolean[items.size()];
            for (int i = 0, n = items.size(); i < n; ++i) this.used[i] = false;
            this.indexes = new int[k];
            for (int i = 0; i < k; ++i) this.indexes[i] = -1;
            generate();
        }

        @Override
        public boolean hasNext() {
            return hasNext;
        }

        @Override
        public List<T> next() {
            if (!hasNext) throw new NoSuchElementException();
            List<T> result = currentPerm;
            generate();
            return result;
        }

        private void generate() {
            hasNext = prepareNext();
            currentPerm = getList();
            hasNext = postNext();
        }

        private boolean prepareNext() {
            
            if (deep == k) {
                return true;
            }

            int n = items.size();
            int idx = this.indexes[deep] + 1;
            while (idx < n) {
                if (!this.used[idx]) break;
                idx++;
            }

            if (idx < n) {
                this.used[idx] = true;
                this.indexes[deep] = idx;
                deep++;
                return prepareNext();
            }

            if (deep == 0) {
                return false;
            }

            for (int i = deep; i < k; ++i) {
                if (this.indexes[i] == -1) continue;
                this.used[this.indexes[i]] = false;
                this.indexes[i] = -1;
            }
            deep--;
            this.used[this.indexes[deep]] = false;
            return prepareNext();
        }

        private boolean postNext() {
            if (deep == 0) {
                return false;
            }
            deep--;
            this.used[this.indexes[deep]] = false;
            if (this.indexes[deep] == items.size() - 1) {
                this.indexes[deep] = -1;
                return postNext();
            }
            return true;
        }

        private List<T> getList() {
            if (Arrays.stream(this.indexes).anyMatch(x -> x == -1)) return null;
            return Arrays.stream(this.indexes)
                    .mapToObj(x -> this.items.get(x))
                    .toList();
        }
    }

    // public static void main(String[] args) {
    //     Permutations<String> permutations = new Permutations<>(
    //             Arrays.asList("A", "B", "C", "D"), 4
    //     );
    //     for (List<String> perm : permutations) {
    //         System.out.println(perm);
    //     }
    // }
}