class SpiralMatrixBuilder {

    final private static int [] DIR_I = {1, 0, -1, 0};
    final private static int [] DIR_J = {0, -1, 0, 1};

    int[][] buildMatrixOfSize(int size) {
        
        final int maxCounter = size * size;
        int[][] spiral = new int[size][size];

        int pos_i = 0, pos_j = -1;
        int stepCounter = 3;
        int stepSize = size;
        int counter = 1;

        while (counter <= maxCounter) {
            for (int i = 0; i < stepSize && counter <= maxCounter; ++i) {
                pos_i += DIR_I[stepCounter];
                pos_j += DIR_J[stepCounter];
                spiral[pos_i][pos_j] = counter++;
            }
            stepCounter += 1; stepCounter %= 4;
            if (stepCounter % 2 == 0) stepSize -= 1;
        }

        return spiral;
    }
}
