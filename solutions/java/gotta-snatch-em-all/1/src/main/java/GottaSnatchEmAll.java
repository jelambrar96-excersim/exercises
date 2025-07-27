import java.util.HashSet;
import java.util.List;
import java.util.Set;

class GottaSnatchEmAll {

    static Set<String> newCollection(List<String> cards) {
        return Set.copyOf(cards);
    }

    static boolean addCard(String card, Set<String> collection) {
        return collection.add(card);
    }

    static boolean canTrade(Set<String> myCollection, Set<String> theirCollection) {

        Set<String> r = new HashSet<String>(myCollection);
        Set<String> t = new HashSet<String>(theirCollection);

        r.removeAll(theirCollection);
        t.removeAll(myCollection);
        
        return !r.isEmpty() && !t.isEmpty();
    }

    static Set<String> commonCards(List<Set<String>> collections) {
        int n = collections.size();
        if (n == 0) {
            return new HashSet<String>();
        }
        Set<String> common = new HashSet<String>(collections.getFirst());
        if (n == 1) {
            return common;
        }
        for (int i = 1; i < n; ++i) {
            if (common.isEmpty()) {
                return common;
            }
            common.retainAll(collections.get(i));
        }
        return common;
    }

    static Set<String> allCards(List<Set<String>> collections) {
        int n = collections.size();
        Set<String> all = new HashSet<String>();
        for (int i = 0; i < n; ++i) {
            all.addAll(collections.get(i));
        }
        return all;
    }
}
