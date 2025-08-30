import java.util.stream.IntStream;
import java.util.Random;

public class Cipher {

    final static int m = 'z' - 'a' + 1;
    private String key;

    public Cipher() {
        Random r = new Random();
        this.key = IntStream.range(0, 100)
                            .map(x -> r.nextInt('a', 'z'))
                            .collect(
                                StringBuilder::new,
                                StringBuilder::appendCodePoint,
                                StringBuilder::append
                            )
                            .toString();
    }

    public Cipher(String key) {
        this.key = key;
    }

    public String getKey() {
        return this.key; 
    }

    public String encode(String plainText) {
        String lowerText = plainText.toLowerCase();
        int keySize = this.key.length();
        return IntStream.range(0, lowerText.length())
                        .map(i -> {
                            char c = lowerText.charAt(i);
                            return Character.isDigit(c) ? c :
                                ((c + this.key.charAt(i % keySize) - 2 * 'a') % m + 'a');
                        })
                        .collect(
                            StringBuilder::new, 
                            StringBuilder::appendCodePoint,
                            StringBuilder::append
                        )
                        .toString();
    }

    public String decode(String cipherText) {
        int keySize = this.key.length();
        return IntStream.range(0, cipherText.length())
                        .map(i -> {
                            char c = cipherText.charAt(i);
                            return Character.isDigit(c) ? c :
                                ((c - this.key.charAt(i % keySize) + m) % m + 'a');                        
                        })
                        .collect(
                            StringBuilder::new, 
                            StringBuilder::appendCodePoint,
                            StringBuilder::append
                        )
                        .toString();
    }
}
