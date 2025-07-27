class NaturalNumber {

    private Classification classification;

    NaturalNumber(int number) {
        if (number < 1) {
            throw new IllegalArgumentException("You must supply a natural number (positive integer)");
        }
        int sum = 0;
        int limit = (number % 2 == 0 ? number / 2 : number / 3);
        for (int i=1; i <= limit; ++i) {
            if (number % i == 0) sum += i;
        }
        this.classification = sum > number ? Classification.ABUNDANT : 
            (sum < number ? Classification.DEFICIENT : Classification.PERFECT);
    }

    Classification getClassification() {
        return this.classification;
    }

}
