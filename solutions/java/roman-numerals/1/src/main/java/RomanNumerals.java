class RomanNumerals {

    final int number;
    final String romanNumber;

    final static String [][] romanTable = new String[][] {
        {"V", "I"}, {"L", "X"}, {"D", "C"}, {"", "M"}
    };

    RomanNumerals(int number) {
        this.number = number;
        if (number < 1 || number > 3999) {
            throw new IllegalArgumentException("Invalid input");
        }
        StringBuilder sBuilder = new StringBuilder();
        for (int i = 0, n = romanTable.length; i < n; ++i) {
            String one = romanTable[i][1], five = romanTable[i][0];
            int lastDigit = number % 10;
            String appendNumber = switch(lastDigit) {
                case 9 -> one + romanTable[i + 1][1];
                case 8 -> five + one + one + one;
                case 7 -> five + one + one;
                case 6 -> five + one;
                case 5 -> five;
                case 4 -> one + five;
                case 3 -> one + one + one;
                case 2 -> one + one;
                case 1 -> one;
                default -> ""; // 0
            };
            sBuilder.insert(0, appendNumber);
            number /= 10;
            if (number == 0) break;
        }
        this.romanNumber = sBuilder.toString();
    }

    String getRomanNumeral() {
        return this.romanNumber;
    }
}
