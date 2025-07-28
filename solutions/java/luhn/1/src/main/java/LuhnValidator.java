import java.lang.Character;
import java.lang.String;
import java.lang.StringBuilder;
import java.util.stream.IntStream;


class LuhnValidator {

    private static int[] LUSH_TABLE;
    static {
        LUSH_TABLE = new int[] {0, 2, 4, 6, 8, 1, 3, 5, 7, 9};
    }

    public boolean isValid(String candidate) {
        
        String cardString = candidate.replace(" ", "");
        if (cardString.length() <= 1) return false;
        if (!cardString.chars().allMatch(Character::isDigit)) return false;

        int [] digitsList = new StringBuilder(cardString)
                                .reverse()
                                .chars()
                                .map(Character::getNumericValue)
                                .toArray();

        int sum = IntStream.range(0, digitsList.length)                        
                    .map(i -> (i % 2 == 0) ? digitsList[i] : LUSH_TABLE[ digitsList[i]])
                    .sum();

        return sum % 10 == 0;
    }

}
