class NaturalNumber {

    private Classification classification;

    NaturalNumber(int number) {
        int sum = 0;
        int limit = (number % 2 == 0 ? number / 2 : number / 3);
        for (int i=2; i <= limit; ++i) {
            if (number % limit == 0) sum += limit;
        }
        this.classification = sum > number ? Classification.ABUNDANT : 
            (sum < number ? Classification.DEFICIENT : Classification.PERFECT);
    }

    Classification getClassification() {
        return this.classification;
    }
}
