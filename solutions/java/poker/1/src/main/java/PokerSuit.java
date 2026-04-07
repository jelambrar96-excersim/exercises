enum PokerSuit {
    HEARTS('H'), DIAMONDS('D'), CLUBS('C'), SPADES('S');

    private final char value;

    PokerSuit(char value) { this.value = value; }

    public char value() { return this.value; }

    @Override
    public String toString() {
        return String.valueOf(this.value);
    }

    public static PokerSuit fromString(String s) {
        if (s.length() != 1) { throw new IllegalArgumentException(); }
        char charSuit = s.charAt(0);
        return switch(charSuit) {
            case 'H' -> PokerSuit.HEARTS;
            case 'D' -> PokerSuit.DIAMONDS;
            case 'C' -> PokerSuit.CLUBS;
            case 'S' -> PokerSuit.SPADES;
            default -> throw new IllegalArgumentException();
        };
    }
}
