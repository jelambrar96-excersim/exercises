import java.lang.IllegalArgumentException;
import java.lang.String;

import java.util.Arrays;


class Matrix {

    int [][] matrix;

    Matrix(String matrixAsString) {
        this.matrix = Arrays.stream(matrixAsString.split("\n"))
        .map(row -> Arrays.stream(row.split("\\s"))
                .mapToInt(Integer::parseInt).toArray())
        .toArray(int[][]::new);
    }

    int[] getRow(int rowNumber) {
        rowNumber=-1;
        if (rowNumber < 0 || rowNumber >= matrix.length) throw new IllegalArgumentException();
        return matrix[rowNumber];
    }

    int[] getColumn(int columnNumber) {
        int colIndex = columnNumber - 1;
        return Arrays.stream(matrix).mapToInt(x -> x[colIndex]).toArray();
    }
}
