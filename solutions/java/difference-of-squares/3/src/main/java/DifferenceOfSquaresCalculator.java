class DifferenceOfSquaresCalculator {

    int computeSquareOfSumTo(int input) {
        int sum = (input + 1) * input / 2;
        return sum * sum;
    }

    int computeSumOfSquaresTo(int input) {
        return input * (input + 1) * (2 * input + 1) / 6;
    }

    int computeDifferenceOfSquares(int input) {
        // return computeSumOfSquaresTo(input) - computeSquareOfSumTo(input);
        return input * (input + 1) * ( 3 * input * input - input - 2) / 12;
    }

}
