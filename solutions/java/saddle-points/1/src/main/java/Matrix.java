import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;

class Matrix {

    private List<List<Integer>> matrix;
    private int h,w;

    Matrix(List<List<Integer>> values) {
        this.matrix = values;
        this.h = matrix.size();
        this.w = h > 0 ? matrix.get(0).size() : 0;
    }

    Set<MatrixCoordinate> getSaddlePoints() {

        HashSet<MatrixCoordinate> sadlePoints = new HashSet<MatrixCoordinate>();
        if (h == 0 || w == 0) return sadlePoints;

        List<Integer> minCols = IntStream.range(0, w)
                                    .map(
                                        x-> IntStream.range(0, h)
                                                .map(y -> this.matrix.get(y).get(x))
                                                .min()
                                                .getAsInt())
                                    .boxed()
                                    .toList();

        for(int i = 0; i < h; ++i) {
            Integer maxI = Collections.max(this.matrix.get(i));
            for (int j = 0; j < w; ++j) {
                Integer e = this.matrix.get(i).get(j);
                if (e == maxI && e == minCols.get(j)) {
                    sadlePoints.add(new MatrixCoordinate(i + 1, j + 1));
                }
            }
        }
        return sadlePoints;
    }
}
