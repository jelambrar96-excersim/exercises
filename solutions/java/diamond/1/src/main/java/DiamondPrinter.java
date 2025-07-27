import java.lang.IllegalArgumentException;
import java.lang.String;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class DiamondPrinter {

    private static char BLANK_CHAR = ' ';

    private static String generateString(int length, char fillChar) {
        if (length <= 0) return "";
        char[] charArray = new char[length];
        Arrays.fill(charArray, fillChar); // Fills the array with the specified character
        return new String(charArray); // Creates a new String from the character array
    }

    List<String> printToList(char a) {
        if (a < 'A' && a < 'Z') throw new IllegalArgumentException("Invalid character");

        int ind = (int)a - 65;
        List<String> diamond = new ArrayList<String>(2 * ind + 1);
        for (int i = 0; i <= ind; ++i) {
            String s1 = generateString(ind - i,  BLANK_CHAR);
            String s2 = generateString(2 * i - 1,  BLANK_CHAR);
            String s3 = String.valueOf((char)(65 + i));
            String row = i == 0 ? (s1 + s3 + s1) : (s1 + s3 + s2+ s3 + s1);
            diamond.add(row);
        }
        
        for (int i = 0; i < ind; ++i) {
            String row = diamond.get(ind - i - 1);
            diamond.add(row);
        }
        return diamond;
    }

}
