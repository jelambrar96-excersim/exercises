import java.util.Arrays;


class Triangle {

    double [] sides;

    Triangle(double side1, double side2, double side3) throws TriangleException {
        this.sides = new double[] {side1, side2, side3};
        
        double sumSides = Arrays.stream(sides).sum();
        if (sumSides == 0) throw new TriangleException();

        double maxSide = Arrays.stream(sides).max().getAsDouble();
        if (maxSide * 2 > sumSides) throw new TriangleException();

        if (Arrays.stream(sides).anyMatch(x -> x < 0)) throw new TriangleException();
    }

    boolean isEquilateral() {
        return Arrays.stream(this.sides).distinct().count() == 1;
    }

    boolean isIsosceles() {
        return Arrays.stream(sides).distinct().count() < 3;
    }

    boolean isScalene() {
        return Arrays.stream(sides).distinct().count() == 3;
    }

}
