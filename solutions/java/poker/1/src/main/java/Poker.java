import java.util.List;

class Poker {

    private List<PokerHand> bestHands;

    Poker(List<String> hand) {
        List<PokerHand> pokerHands = hand.stream().map(PokerHand::fromString).toList();
        PokerHand bestHand = pokerHands.stream().sorted(PokerHand::compareTo).toList().getLast();
        this.bestHands = pokerHands.stream().filter(h -> h.compareTo(bestHand) == 0).toList();
    }

    List<String> getBestHands() {
        return this.bestHands.stream().map(hand -> hand.toString()).toList();
    }

}
