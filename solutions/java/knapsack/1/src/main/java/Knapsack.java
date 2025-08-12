import java.util.List;

class Knapsack {

    private static int maximumValueHelper(int maximumWeight, List<Item> items, int index) {
        int n = items.size();
        if (maximumWeight <= 0) return 0;
        int currentMaxValue = 0;
        for (int newIndex = index; newIndex < n; newIndex++) {
            Item currentItem = items.get(newIndex);
            if (currentItem.weight > maximumWeight) continue;
            int currentValue = currentItem.value 
                + maximumValueHelper(maximumWeight - currentItem.weight, items, newIndex + 1);
            if (currentValue > currentMaxValue) currentMaxValue = currentValue;
        }
        return currentMaxValue;
    }

    public int maximumValue(int maximumWeight, List<Item> items) {
        return maximumValueHelper(maximumWeight, items, 0);
    }

}
