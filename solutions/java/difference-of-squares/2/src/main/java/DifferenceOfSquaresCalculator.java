class DifferenceOfSquaresCalculator {

    int computeSquareOfSumTo(int input) {
        return (input + 1) * input / 2;
    }

    int computeSumOfSquaresTo(int input) {
        int sum = input * (input + 1) * (2 * input + 1) / 6;
        return sum * sum;
    }

    int computeDifferenceOfSquares(int input) {
        return input * (input + 1) * ( 3 * input * input - input - 2) / 12;
    }

}
