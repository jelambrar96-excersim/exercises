import java.util.Arrays;
import java.util.List;

class FlowerFieldBoard {


    int sumMatrix[][];

    FlowerFieldBoard(List<String> boardRows) {

        if (boardRows.isEmpty()) {
            this.sumMatrix = new int[0][];
            return;
        }
        if (boardRows.size() == 1 && boardRows.get(0).isEmpty()) {
            this.sumMatrix = new int[1][0];
            return;
        }

        int [][] originalMatrix = boardRows.stream()
                .map(row -> row.chars()
                                .map(c -> c == '*' ? 1 : 0)
                                .toArray())
                .toArray(int[][]::new);

        int [][] auxTick = auxTick(originalMatrix);
        sumMatrix = generteMatrix(auxTick.length - 2, auxTick[0].length - 2);

        for (int i = 1; i < auxTick.length - 1; ++i) {
            for (int j = 1; j < auxTick[i].length - 1; ++j) {
                sumMatrix[i - 1][j - 1] = auxTick[i][j] == 1 ? -1 : getsum(auxTick, i, j);
            }
        }
    
    }

    List<String> withNumbers() {
        return Arrays.stream(this.sumMatrix)
                .map(row -> Arrays.stream(row)
                        .mapToObj(num -> switch(num) {
                            case -1 -> "*";
                            case 0 -> " ";
                            default -> String.valueOf(num);
                        })
                        .reduce((a, b) -> a + b)
                        .orElse(""))
                .toList();
    }


    private static int[][] auxTick(int[][] matrix) {

        int h = matrix.length;
        int w = matrix[0].length;
        
        int [][] auxTick = generteMatrix(h + 2, w + 2);

        // fill internal matrix
        for (int i = 0; i < h; ++i) {
            for (int j = 0; j < w; ++j) {
                auxTick[i + 1][j + 1] = matrix[i][j];
            }
        }

        // fill border with zeros
        for (int i = 0; i < h + 2; ++i) {
            auxTick[i][0] = 0;
            auxTick[i][w + 1] = 0;
        }
        for (int i = 0; i < w + 2; ++i) {
            auxTick[0][i] = 0;
            auxTick[h + 1][i] = 0;
        }

        return auxTick;
    }

    private static int getsum(int[][] matrix, int posh, int posw) {
        int sum = 0;
        for (int i = -1; i < 2; ++i) {
            for (int j = -1; j < 2; ++j) {
                if (i == 0 && j == 0) continue;
                sum += matrix[posh + i][posw + j];
            }
        }
        return sum;
    }

    private static int [][] generteMatrix(int h, int w) {
        int [][] auxTick = new int[h][];
        for (int i = 0; i < h; ++i) {
            auxTick[i] = new int[w];
        }
        return auxTick;
    }

}