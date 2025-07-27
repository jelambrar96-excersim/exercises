import java.lang.String;


class IsbnVerifier {

    boolean isValid(String stringToVerify) {
        
        int n = stringToVerify.length();
        if (n < 10 || n > 13) return false;

        String isbn = stringToVerify.replace("-", "");
        n = isbn.length();
        if (n < 9 || n > 10) return false;

        if (n == 9) {
            n = 10; 
            isbn = isbn + "0";
        }

        int sum = 0;
        char [] charArrray =  isbn.toCharArray();
        for (int i = 1; i <= n; ++i) {
            char ch = charArrray[n - i];
            if (ch == 'X' && i == 1) {
                sum += 10;
                continue;
            }
            if (!Character.isDigit(ch))return false;
            sum += (Character.getNumericValue(ch) * i);
        }
        return sum % 11 == 0;
    }

}
