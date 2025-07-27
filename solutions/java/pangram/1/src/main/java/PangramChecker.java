import java.lang.String;

public class PangramChecker {

    public boolean isPangram(String input) {
        return input.toLowerCase().chars()
            .filter(x -> x >= 'a' && x <= 'z')
            .distinct()
            .count() == 26;
    }

}
