import java.lang.Integer;
import java.util.LinkedHashSet;


class SumOfMultiples {

    int sumMultiples;

    SumOfMultiples(int number, int[] set) {
        LinkedHashSet<Integer> multiples = new LinkedHashSet<Integer>();
        for (int i: set) {
            if (i == 0) continue;
            for (int j = i; j < number; j+= i) {
                multiples.add(j);
            }
        }
        this.sumMultiples = multiples.stream().reduce(0, (x, y) -> x + y);
    }

    int getSum() {
        return this.sumMultiples;
    }

}
