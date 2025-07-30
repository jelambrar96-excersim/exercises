import java.util.LinkedList;
import java.util.List;

class PythagoreanTriplet {

    private int a,b,c;

    PythagoreanTriplet(int a, int b, int c) {
        this.a = a; 
        this.b = b; 
        this.c = c;
    }

    public String toString() {
        return String.format("(%d, %d, %d)", a, b, c);
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if ( o instanceof PythagoreanTriplet) {
            PythagoreanTriplet pt = (PythagoreanTriplet) o;
            return a == pt.a && b == pt.b && c == pt.c;
        } 
        return false;
    }

    static TripletListBuilder makeTripletsList() {
        return new TripletListBuilder();
    }

    static class TripletListBuilder {

        private Integer sum = null;
        private Integer maxFactor = null;

        TripletListBuilder thatSumTo(int sum) {
            this.sum = sum;
            return this;
        }

        TripletListBuilder withFactorsLessThanOrEqualTo(int maxFactor) {
            this.maxFactor = maxFactor;
            return this;
        }

        List<PythagoreanTriplet> build() {

            if (this.sum == null) throw new IllegalArgumentException("No initalices sum");
            int maxC = this.maxFactor == null ? this.sum / 2 : this.maxFactor;
            
            int n = this.sum;
            List<PythagoreanTriplet> listPT = new LinkedList<PythagoreanTriplet>();
            for (int a = 1; a < this.sum / 3; ++a) {

                int cNum = a * a + (n - a) * (n - a);
                int cDen = 2 * (n - a);
                if (cNum % cDen != 0) continue;
                
                int c = cNum / cDen;
                if (c > maxC) continue;

                int b = n - (a + c);
                if (b < a) break;

                int sumCat = a * a + b * b;
                int sumH = c * c;

                if (sumCat == sumH) listPT.add(new PythagoreanTriplet(a, b, c));
            }
            return listPT;
        }

    }

    public static void main(String [] args) {
        List<PythagoreanTriplet> actual
                = PythagoreanTriplet
                        .makeTripletsList()
                        .thatSumTo(12)
                        .build();
        System.out.println(actual.size());
    }

}