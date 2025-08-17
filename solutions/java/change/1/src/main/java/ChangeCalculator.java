import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

class ChangeCalculator {

    private List<Integer> coins;
    private final Map<Integer, List<Integer>> memo = new HashMap<>();


    ChangeCalculator(List<Integer> currencyCoins) {
        this.coins = currencyCoins;
        this.coins.sort(Comparator.reverseOrder());
    }

    private List<Integer> computeMostEfficientChangeRec(int grandTotal) {
        if (grandTotal == 0) { return new LinkedList<Integer>(); }
        if (memo.containsKey(grandTotal)) {
            return memo.get(grandTotal);
        }
        List<Integer> bestCoins = null;
        int bestSize = grandTotal;
        for (int coin: this.coins) {
            if (coin > grandTotal) { continue; }
            List<Integer> tempCoinList = computeMostEfficientChangeRec(grandTotal - coin);
            if (tempCoinList == null) { continue; }
            int tempCoinListSize = tempCoinList.size() + 1;
            if (bestCoins == null || tempCoinListSize < bestSize) {
                bestCoins = new LinkedList<>();
                bestCoins.add(coin);
                bestCoins.addAll(tempCoinList);
                bestSize = tempCoinListSize;               
            }
        }
        memo.put(grandTotal, bestCoins);
        return bestCoins;
    }

    List<Integer> computeMostEfficientChange(int grandTotal) {
        if (grandTotal < 0) throw new IllegalArgumentException("Negative totals are not allowed.");
        List<Integer> temp = computeMostEfficientChangeRec(grandTotal);
        if (temp == null) throw new IllegalArgumentException(
            "The total " + Integer.toString(grandTotal) + " cannot be represented in the given currency.");
        temp.sort(Integer::compareTo);
        return temp;
    }

}
