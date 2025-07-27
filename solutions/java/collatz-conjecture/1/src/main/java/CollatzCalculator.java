import java.lang.IllegalArgumentException;


class CollatzCalculator {

    int computeStepCount(int start) {
        if (start < 1) {
            throw new IllegalArgumentException("Only positive integers are allowed");
        }
        int loopCounter = 0;
        while (start > 1) {
            start = start % 2 == 0 ? start / 2 : (3 * start + 1);
            loopCounter++;
        }
        return loopCounter;
    }

}
