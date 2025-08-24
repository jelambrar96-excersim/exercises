import java.util.List;

class OpticalCharacterReader {

    List<String> TABLE = List.of(
            " _ | ||_|   ", // 0
            "     |  |   ", // 1
            " _  _||_    ", // 2
            " _  _| _|   ", // 3
            "   |_|  |   ", // 4
            " _ |_  _|   ", // 5
            " _ |_ |_|   ", // 6
            " _   |  |   ", // 7
            " _ |_||_|   ", // 8
            " _ |_| _|   "  // 9
    );

    String parse(List<String> input) {
        int rows = input.size();
        if (rows % 4 != 0|| rows <= 0) {
            throw new IllegalArgumentException("Number of input rows must be a positive multiple of 4");
        }
        List<Integer> colsList = input.stream().mapToInt(String::length).distinct().boxed().toList();
        if (colsList.size() != 1) {
            throw new IllegalArgumentException("All input rows must have the same number of columns");
        }
        int cols = colsList.stream().findFirst().orElse(0);
        if (cols % 3 != 0|| cols <= 0) {
            throw new IllegalArgumentException("Number of input columns must be a positive multiple of 3");
        }
        StringBuilder result = new StringBuilder();
        for (int r = 0; r < rows; r += 4) {
            if (r > 0) {
                result.append(",");
            }
            for (int i = 0; i < cols; i += 3) {
                StringBuilder digit = new StringBuilder();
                for (int j = r, maxj = r + 4; j < maxj; j++) {
                    digit.append(input.get(j), i, i + 3);
                }
                int index = TABLE.indexOf(digit.toString());
                result.append(index == -1 ? "?" : index);
            }
        }
        return result.toString();
    }

}
