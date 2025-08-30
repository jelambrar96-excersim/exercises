import java.util.Arrays;

class Connect {

    final char SYMBOL_TOP_DOWN = 'O';
    final char SYMBOL_LEFT_RIGHT = 'X';
    final char[][] matrix;

    public Connect(String[] board) {
        int hBoard = board.length;
        this.matrix = new char[hBoard][];
        for (int i = 0; i < hBoard; ++i) {
            this.matrix[i] = board[i].replaceAll("\\s+", "").toCharArray();
        }
    }

    public Winner computeWinner() {
        
        // for x player
        Boolean[][] visitedX = new Boolean[matrix.length][matrix[0].length];
        for (int i = 0, n = matrix.length; i < n; ++i) Arrays.fill(visitedX[i], null);
        for (int j = 0, n = matrix[0].length; j < n; ++j) {
            boolean isWinner = isWinner(matrix, SYMBOL_TOP_DOWN, 0, j, visitedX);
            if (isWinner) { return Winner.PLAYER_O; }
        }

        char[][] matrixO = transpose(matrix);
        Boolean[][] visitedO = new Boolean[matrixO.length][matrixO[0].length];
        for (int i = 0, n = matrixO.length; i < n; ++i) Arrays.fill(visitedO[i], null);
        for (int j = 0, n = matrixO[0].length; j < n; ++j) {
            boolean isWinner = isWinner(matrixO, SYMBOL_LEFT_RIGHT, 0, j, visitedO);
            if (isWinner) { return Winner.PLAYER_X; }
        }
        return Winner.NONE;
    }

    private static char[][] transpose(char[][] matrix) {
        int width = matrix.length;
        int height = matrix[0].length;
        char[][] outMatrix = new char[height][width];
        for (int i = 0; i < height; ++i) {
            for (int j = 0; j < width; ++j) {
                outMatrix[i][j] = matrix[j][i];
            }
        }
        return outMatrix;
    }

    private boolean isWinner(
        char[][] matrix,
        char playerSymbol,
        int ipos, int jpos,
        Boolean[][] visited) {
        
        if (matrix[ipos][jpos] != playerSymbol) { return false; }

        int matrixHeight = matrix.length;
        if (ipos == matrixHeight - 1) { return true; }
        
        Boolean visitedValue = visited[ipos][jpos];
        if (visitedValue != null) { return visitedValue; }
        visited[ipos][jpos] = false; // avoid stack overflow

        // Explore all valid neighboring positions
        boolean winFlag = false;
        int matrixWidth = matrix[0].length;
        for (int i = -1; i < 2; ++i) {
            int ni = ipos + i;
            if (ni < 0) { continue; }
            for (int j = -1; j < 2; ++j) {
                int nj = jpos + j;
                if (nj < 0 || nj >= matrixWidth || j == i) {
                    continue;
                }
                winFlag = isWinner(matrix, playerSymbol, ni, nj, visited);
                if (winFlag) { break; }
            }
            if (winFlag) { break; }
        }
        visited[ipos][jpos] = winFlag;
        return winFlag;
    }
}
