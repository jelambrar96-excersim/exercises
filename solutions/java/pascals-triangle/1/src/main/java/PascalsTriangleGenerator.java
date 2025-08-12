class PascalsTriangleGenerator {

    int[][] generateTriangle(int rows) {

        if (rows == 0) return new int[0][];
        int[][] triangle = new int[rows][];

        triangle[0] = new int[]{1};
        for (int i = 1; i < rows; ++i) {
            triangle[i] = new int[i + 1];
            triangle[i][0] = 1;
            triangle[i][i] = 1;
            for (int j = 1; j < i; ++j) {
                triangle[i][j] = triangle[i - 1][j] + triangle[i - 1][j - 1];
            }
        }
        return triangle;
    }

}