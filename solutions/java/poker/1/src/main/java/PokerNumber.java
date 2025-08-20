import java.util.Arrays;
import java.util.List;

enum PokerNumber {
    AS(14), TWO(2), THREE(3), FOUR(4), FIVE(5),
    SIX(6), SEVEN(7), EIGHT(8), NINE(9), TEN(10),
    JACK(11), QUEEN(12), KING(13); 
    
    private final int value;
    private static List<PokerNumber> pokerNumbers = Arrays.asList(
        null, null , PokerNumber.TWO, PokerNumber.THREE,
        PokerNumber.FOUR, PokerNumber.FIVE, PokerNumber.SIX, PokerNumber.SEVEN,
        PokerNumber.EIGHT, PokerNumber.NINE, PokerNumber.TEN, PokerNumber.JACK,
        PokerNumber.QUEEN, PokerNumber.KING, PokerNumber.AS);

    PokerNumber(int value) { this.value = value; }
    
    public int value() { return this.value; }

    public PokerNumber prev(int i) {
        int ind = (this.value + 11 - i) % 13 + 2;
        return pokerNumbers.get(ind);
    }

    public PokerNumber next(int i) {
        int ind = (this.value - 2 + i) % 13 + 2;
        return pokerNumbers.get(ind);
    }
    
    @Override
    public String toString() {
        return switch (this.value) {
            case 14 -> "A";
            case 11 -> "J";
            case 12 -> "Q";
            case 13 -> "K";
            default -> Integer.toString(this.value);
        };
    }

    public static PokerNumber fromInt(Integer i) {
        return PokerNumber.pokerNumbers.get(i);
    } 

    public static PokerNumber fromString(String s) {
        return switch (s) {
            case "A" -> PokerNumber.AS;
            case "J" -> PokerNumber.JACK;
            case "Q" -> PokerNumber.QUEEN;
            case "K" -> PokerNumber.KING;
            default -> PokerNumber.fromInt(Integer.parseInt(s));
        };
        
    }
}