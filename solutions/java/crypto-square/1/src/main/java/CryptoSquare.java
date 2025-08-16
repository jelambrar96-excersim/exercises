import java.lang.Math;


class CryptoSquare {

    private String cipherText;

    CryptoSquare(String plaintext) {
        
        String nomalizedString = plaintext
            .toLowerCase()
            .replaceAll("[^a-zA-Z0-9]", "");
        
        int lenText = nomalizedString.length();

        int cols = (int)Math.ceil(Math.sqrt(lenText));
        int rows = (cols * (cols - 1) >= lenText && cols > 0) ? (cols - 1) : cols;

        Character[][] matrix = new Character[rows][cols];
        int counter = 0;
        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < cols; ++j) {
                matrix[i][j] = counter < lenText ? nomalizedString.charAt(counter) : ' ';
                counter++;
            }
        }

        StringBuilder textBuilder = new StringBuilder();
        for (int j = 0; j < cols; ++j) {
            for (int i = 0; i < rows; ++i) {
                textBuilder.append(matrix[i][j]);
            }
            if (j < cols - 1) textBuilder.append(' ');
        }
        
        // this.cipherText = textBuilder.toString()
        //     .chars()
        //     .collect(
        //         StringBuilder::new, (builder, element) -> {
        //             int blen = builder.length();
        //             if (blen % rows == (rows - 1) && blen > 0) {
        //                 builder.append(" ");
        //             }
        //             builder.append((char) element);
        //         }, 
        //         StringBuilder::append
        //     )
        //     .toString();
        this.cipherText = textBuilder.toString();
    }

    String getCiphertext() {
        return this.cipherText;
    }

}
