import java.lang.IllegalArgumentException;
import java.lang.String;

import java.util.Arrays;


class Matrix {

    int [][] matrix;

    Matrix(String matrixAsString) {
        String [] rows = matrixAsString.split("\\n");
        int n = rows.length;
        this.matrix = new int[n][];
        for (int i = 0; i < n; ++i) {
            matrix[i] = Arrays.stream(rows[i].split("\\s+"))
                .mapToInt(Integer::parseInt).toArray();
        }
    }

    int[] getRow(int rowNumber) {
        rowNumber--;
        if (rowNumber < 0 || rowNumber >= matrix.length) throw new IllegalArgumentException();
        return matrix[rowNumber];
    }

    int[] getColumn(int columnNumber) {
        int colIndex = columnNumber - 1;
        return Arrays.stream(matrix).mapToInt(x -> x[colIndex]).toArray();
    }
}
