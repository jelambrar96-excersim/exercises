class RectangleCounter {

    int countRectangles(String[] grid) {
        
        if (grid == null || grid.length == 0) {
            return 0;
        }
        if (grid[0].isEmpty()) {
            return 0;
        }

        char[][] matrix = new char[grid.length][];
        for (int i = 0; i < grid.length; i++) {
            matrix[i] = grid[i].toCharArray();
        }

        int rows = matrix.length;
        int cols = rows > 0 ? matrix[0].length : 0;

        int rectangleCount = 0;
 
        // Iterate through each cell in the matrix
        for (int i = 0; i < rows - 1; i++) {
            for (int j = 0; j < cols - 1; j++) {        
                if (matrix[i][j] != '+') {
                    continue;
                }

                // Check for potential rectangles starting from (i, j)
                for (int hsize = 1, maxHsize = rows - i; hsize < maxHsize; hsize++) {
                    for (int wsize = 1, maxWsize = cols - j; wsize < maxWsize; wsize++) {

                        // Check if the corners of the rectangle are valid
                        if (matrix[i + hsize][j] != '+' || 
                            matrix[i][j + wsize] != '+' || 
                            matrix[i + hsize][j + wsize] != '+') {
                            continue;
                        }
                     
                        boolean isRectangle = true;
                        // Check vertical and horizontal edges
                        for (int k = i + 1; k < i + hsize; k++) {
                            if ((matrix[k][j] != '|' && matrix[k][j] != '+') ||
                                (matrix[k][j + wsize] != '|' && matrix[k][j + wsize] != '+')) {
                                isRectangle = false;
                                break;
                            }
                        }
                        if (!isRectangle) {
                            continue;
                        }

                        // Check horizontal edges
                        for (int k = j + 1; k < j + wsize; k++) {
                            if ((matrix[i][k] != '-' && matrix[i][k] != '+') || 
                                (matrix[i + hsize][k] != '-' && matrix[i + hsize][k] != '+')) {
                                isRectangle = false;
                                break;
                            }
                        }

                        if (isRectangle) {
                            rectangleCount++;
                        }
                    }
                }
            }
        }
        return rectangleCount;
    }

}