class GameOfLife {
    
    public int[][] tick(int[][] matrix) {
        
        int h = matrix.length;
        if (h == 0) return new int[0][];
    
        int w = matrix[0].length;
        
        int[][] auxTick = auxTick(matrix);
        int[][] tick = generteMatrix(h, w);

        for (int i = 0; i < h; ++i) {
            for (int j = 0; j < w; ++j) {
                int sum = getsum(auxTick, i + 1, j + 1);
                tick[i][j] =  matrix[i][j] == 1
                                ? (sum == 2 || sum == 3 ? 1 : 0)
                                : (sum == 3 ? 1 : 0);
            }
        }
        return tick;
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
            auxTick[i][h + 1] = 0;
        }
        for (int i = 0; i < w + 2; ++i) {
            auxTick[0][i] = 0;
            auxTick[w + 1][i] = 0;
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
