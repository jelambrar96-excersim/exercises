class RailFenceCipher {

    private int rows;

    RailFenceCipher(int rows) {
        this.rows = rows;
    }

    String getEncryptedData(String message) {
        
        // create matrix
        int messageSize = message.length();
        char[][] lines = new char[this.rows][messageSize];
        for (int i = 0; i < this.rows; ++i) {
            // lines[i] = new char[messageSize];
            for (int j = 0; j < messageSize; ++j) lines[i][j] = 0;
        }

        // fill matrix
        int direction = -1;
        int icol = 0, irow = 0;
        for (char c: message.toCharArray()) {
            lines[irow][icol] = c;
            if (irow == 0 || irow == (this.rows - 1)) direction *= -1;
            irow += direction;
            icol += 1;
        }

        // extract text
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < this.rows; ++i) {
            for (int j = 0; j < messageSize; ++j) {
                char c = lines[i][j];
                if (c == 0) continue;
                builder.append(c);
            }
        }

        return builder.toString();
    }

    String getDecryptedData(String message) {
        
        // create matrix
        int messageSize = message.length();
        char[][] lines = new char[this.rows][messageSize];
        for (int i = 0; i < this.rows; ++i) {
            // lines[i] = new char[messageSize];
            for (int j = 0; j < messageSize; ++j) lines[i][j] = 0;
        }

        // fill matrix with ones
        int direction = -1;
        int irow = 0;
        for (int j = 0; j < messageSize; ++j) {
            lines[irow][j] = 1;
            if (irow == 0 || irow == (this.rows - 1)) direction *= -1;
            irow += direction;
        }
        
        // fill matrix with values
        int k = 0;
        for (int i = 0; i < this.rows; ++i) {
            for (int j = 0; j < messageSize; ++j) {
                char c = lines[i][j];
                if (c == 0) continue;
                lines[i][j] = message.charAt(k++);
            }
        }

        // extract text
        StringBuilder builder = new StringBuilder();
        for (int j = 0; j < messageSize; ++j) {
            for (int i = 0; i < this.rows; ++i) {
                char c = lines[i][j];
                if (c == 0) continue;
                builder.append(c);
            }
        }

        return builder.toString();
    }

}