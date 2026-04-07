class PokerCard {
    public final PokerNumber number;
    public final PokerSuit suit;

    PokerCard(PokerNumber number, PokerSuit suit) {
        this.number = number;
        this.suit = suit;
    }

    public PokerNumber getPokerNumber() { return this.number; }
    public PokerSuit getPokerSuit() { return this.suit; }

    @Override
    public String toString() {
        return this.number.toString() + this.suit.toString();
    }

    public static PokerCard fromString(String input) {
        int n = input.length();
        if (n < 2 || n > 3) { throw new IllegalArgumentException(); }
        PokerNumber number = PokerNumber.fromString(input.substring(0, n - 1));
        PokerSuit suit = PokerSuit.fromString(input.substring(n - 1, n));
        return new PokerCard(number, suit);
    }
}
