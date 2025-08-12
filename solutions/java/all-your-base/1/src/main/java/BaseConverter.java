import java.util.LinkedList;

class BaseConverter {

    private int number;

    BaseConverter(int originalBase, int[] originalDigits) {
        if (originalBase < 2) throw new IllegalArgumentException("Bases must be at least 2.");
        int sum = 0;
        int n = originalDigits.length;
        for (int i = 0; i < n; ++i) {
            int d = originalDigits[i];
            if (d < 0)
                throw new IllegalArgumentException("Digits may not be negative.");
            if (d >= originalBase) 
                throw new IllegalArgumentException("All digits must be strictly less than the base.");
            sum += (int)Math.pow(originalBase, n - i - 1) * d;
        }
        this.number = sum;
    }

    int[] convertToBase(int newBase) {
        if (newBase < 2) 
            throw new IllegalArgumentException("Bases must be at least 2.");
        if (this.number == 0) return new int[]{0};
        int n = this.number;
        LinkedList<Integer> outputDigits = new LinkedList<Integer>();
        while (n > 0) {
            outputDigits.add(0, n % newBase);
            n /= newBase;
        }
        return outputDigits.stream().mapToInt(Integer::intValue).toArray();
    }

}