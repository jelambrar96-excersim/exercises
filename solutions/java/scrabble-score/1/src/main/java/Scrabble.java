import java.lang.String;
import java.lang.IllegalArgumentException;

class Scrabble {

    int score;

    Scrabble(String word) {
        this.score = word.toUpperCase().chars().map(
            (x) -> switch (x) {
                case 'A', 'E', 'I', 'O', 'U', 'L', 'N', 'R', 'S', 'T'   -> 1;
                case 'D', 'G'                                           -> 2;
                case 'B', 'C', 'M', 'P'                                 -> 3;
                case 'F', 'H', 'V', 'W', 'Y'                            -> 4;
                case 'K'                                                -> 5;
                case 'J', 'X'                                           -> 8;
                case 'Q', 'Z'                                           -> 10;
                default -> throw new IllegalArgumentException();
        }).sum();
    }

    int getScore() {
        return this.score;
    }

}
