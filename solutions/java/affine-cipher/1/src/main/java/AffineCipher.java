import java.lang.IllegalArgumentException;

public class AffineCipher {

    final private static int m = 'z' - 'a' + 1;
    
    private static int gcd(int a, int b) {
        return b == 0 ? a : gcd(b,a % b);
    }

    public String encode(String text, int coefficient1, int coefficient2){
        if (gcd(coefficient1, m) > 1) {
            throw new IllegalArgumentException("Error: keyA and alphabet size must be coprime.");
        }
        return text.toLowerCase()
                    .chars()
                    .filter(x -> Character.isAlphabetic(x) || Character.isDigit(x))
                    .map(x-> {
                        return Character.isDigit(x) ? x :
                            (((coefficient1 * (x - 'a') + coefficient2) % m) + 'a');
                    })
                    .collect(StringBuilder::new, (builder, element) -> {
                        if (builder.length() % 6 == 5) {
                            builder.append(" ");
                        }
                        builder.append((char) element);
                    }, StringBuilder::append)
                    .toString();
    }

    private static int decodeInt(int number, int coefficient1, int coefficient2 ) {
        number -= coefficient2;
        while (true) {
            if (number >= 0 && number % coefficient1 == 0) return (number / coefficient1) % m;
            number += m;
        }
    }

    public String decode(String text, int coefficient1, int coefficient2){
        if (gcd(coefficient1, m) > 1) {
            throw new IllegalArgumentException("Error: keyA and alphabet size must be coprime.");
        }
        return text.toLowerCase()
                    .chars()
                    .filter(x -> Character.isAlphabetic(x) || Character.isDigit(x))
                    .map(x-> {
                        return Character.isDigit(x) ? x :
                            (decodeInt(x - 'a', coefficient1, coefficient2) + 'a');
                    })
                    .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                    .toString();
    }
}