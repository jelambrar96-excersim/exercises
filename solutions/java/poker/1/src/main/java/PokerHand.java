
import java.lang.Comparable;
import java.lang.String;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;
import java.util.function.Function;
import java.util.HashSet;
import java.util.Map;


class PokerHand implements Comparable<PokerHand>{
    
    public final List<PokerCard> cards;
    public final PokerRank rank;
    public final List<PokerNumber> sortedNumbers;
    
    PokerHand(List<PokerCard> cards) {
        this.cards = cards;
        FullPokerRank fullPokerRank = computeRank(cards);
        this.rank = fullPokerRank.rank;
        // this.highNumber = fullPokerRank.highNumber;
        this.sortedNumbers = fullPokerRank.sortedNumbers;
    }

    private FullPokerRank computeRank(List<PokerCard> cards) {

        // step 0: list of numbers
        List<PokerNumber> pokerNumbers = cards.stream()
                .map(PokerCard::getPokerNumber).toList();
        List<PokerNumber> sortedPokerNumbers = pokerNumbers.stream()
                .sorted((p0, p1) -> p1.value() - p0.value()).toList();
        int minValue = sortedPokerNumbers.getLast().value();
        int maxValue = sortedPokerNumbers.getFirst().value();
        
        // step 0.5. histogram of numbers
        Map<PokerNumber, Long> porkerNumberHist = pokerNumbers.stream()
                .collect(Collectors.groupingBy(
                    Function.identity(),
                    Collectors.counting()
                ));

        boolean areDistinct = pokerNumbers.stream()
                .distinct().count() == pokerNumbers.size();

        boolean isStraight = false;
        PokerNumber maxStraightNumber = null;
        if (areDistinct) {
            if (porkerNumberHist.containsKey(PokerNumber.AS)) {
                List<Integer> pokerNumAux = pokerNumbers.stream()
                    .map(pn -> pn.value() - 2)
                    .mapToInt(i -> i < 6 ? i : (i - 13))
                    .sorted().boxed().toList();
                if (pokerNumAux.get(2) == -1) {
                    isStraight = false;
                }
                else {
                    int minAux = pokerNumAux.getFirst().intValue();
                    int maxAux = pokerNumAux.getLast().intValue();
                    isStraight = (minAux + 4 == maxAux);
                    if (isStraight) {
                        maxStraightNumber = PokerNumber.fromInt(
                            maxAux < 0 ? (maxAux + 15): (maxAux + 2));
                    }
                }
            }
            else {
                isStraight = (porkerNumberHist.keySet().stream()
                .filter(k -> porkerNumberHist.get(k) == 1).count() == 5)
                && (minValue + 4 == maxValue);
                if (isStraight) {
                    maxStraightNumber = sortedPokerNumbers.getFirst();
                }
            }
        }

        // step 1. number of suits
        long countSuits = cards.stream()
                .map(PokerCard::getPokerSuit).distinct().count();

        // step 2. check if its a unique suit
        if (countSuits == 1) {

            // step 3. check if its a royal flush
            List<PokerNumber> royalFlushList = Arrays.asList(
                PokerNumber.TEN, PokerNumber.JACK, PokerNumber.QUEEN,
                PokerNumber.KING, PokerNumber.AS
            );
            Set<PokerNumber> royalFlushMap = new HashSet<PokerNumber>(royalFlushList);
            boolean isRoyalFlush = pokerNumbers.stream()
                                .filter(number -> royalFlushMap.contains(number))
                                .count() == 5;
            if (isRoyalFlush) { 
                return new FullPokerRank(PokerRank.ROYAL_FLUSH,royalFlushList);
            }

            // step 4. check if its a royal straight
            if (isStraight) { 
                final PokerNumber startStraing = maxStraightNumber; 
                List<PokerNumber> straingNumbers = IntStream.range(0, 5)
                        .mapToObj(i -> startStraing.prev(i)).toList();
                return new FullPokerRank(PokerRank.STRAIGHT_FLUSH, straingNumbers); 
            }
        }

        // step 6. check if its a  for a kind
        if (porkerNumberHist.containsValue(Long.valueOf(4))) { 
            PokerNumber highNumber4 = porkerNumberHist.keySet().stream()
                        .filter(k -> porkerNumberHist.get(k) == 4).toList().get(0);
            PokerNumber highNumber1 = porkerNumberHist.keySet().stream()
                        .filter(k -> porkerNumberHist.get(k) == 1).toList().get(0);
            return new FullPokerRank(
                PokerRank.FOUR_OF_A_KIND,
                Arrays.asList(highNumber4, highNumber4, highNumber4, highNumber4, 
                    highNumber1)
            );
        }

        // step 6. check if its a full house
        if (porkerNumberHist.containsValue(Long.valueOf(2)) 
            && porkerNumberHist.containsValue(Long.valueOf(3))) { 
            PokerNumber highNumber3 = porkerNumberHist.keySet().stream()
                    .filter(k -> porkerNumberHist.get(k) == 3).toList().get(0);
            PokerNumber highNumber2 = porkerNumberHist.keySet().stream()
                    .filter(k -> porkerNumberHist.get(k) == 2).toList().get(0);
            return new FullPokerRank(
                PokerRank.FULL_HOUSE,
                Arrays.asList(highNumber3, highNumber3, highNumber3,
                    highNumber2, highNumber2)
            );
        }
        
        // step 7. check ig its a flush
        if (countSuits == 1) { 
            return new FullPokerRank(PokerRank.FLUSH, sortedPokerNumbers);
        }

        // step 8. check if its a straight
        if (isStraight) {
            final PokerNumber startStraing = maxStraightNumber;
            List<PokerNumber> straingNumbers = IntStream.range(0, 5)
                        .mapToObj(i -> startStraing.prev(i)).toList();
            return new FullPokerRank(PokerRank.STRAIGHT, straingNumbers);
        }

        // step 9. check if a three of a kind
        if (porkerNumberHist.containsValue(Long.valueOf(3))) { 
            List<PokerNumber> highNumbers = porkerNumberHist.keySet().stream()
                        .filter(k -> porkerNumberHist.get(k) == 3).toList();
            List<PokerNumber> lowNumbers = sortedPokerNumbers.stream()
                        .filter(k -> porkerNumberHist.get(k) != 3).toList();
            List<PokerNumber> sortedNumbers = new ArrayList<PokerNumber>();
            sortedNumbers.addAll(Arrays.asList(
                        highNumbers.get(0), highNumbers.get(0), highNumbers.get(0)));
            sortedNumbers.addAll(lowNumbers);
            return new FullPokerRank(PokerRank.THREE_OF_A_KIND, sortedNumbers);
        }

        // step 10. create a new histogram
        Map<Long, Long> counterHistogram = porkerNumberHist.values().stream()
                            .collect(
                                Collectors.groupingBy(
                                    Function.identity(), 
                                    Collectors.counting()
                            ));

        // step 11
        if (counterHistogram.containsKey(Long.valueOf(2))) {
            if (counterHistogram.get(Long.valueOf(2)) == Long.valueOf(2)) {
                List<PokerNumber> highNumbers = sortedPokerNumbers.stream()
                            .filter(k -> porkerNumberHist.get(k) == 2).toList();
                List<PokerNumber> lowNumbers = porkerNumberHist.keySet().stream()
                            .filter(k -> porkerNumberHist.get(k) != 2).toList();
            
                List<PokerNumber> sortedNumbers = Arrays.asList(
                    highNumbers.get(0), highNumbers.get(1),
                    highNumbers.get(2), highNumbers.get(3), lowNumbers.get(0));
                return new FullPokerRank(PokerRank.TWO_PAIRS, sortedNumbers);
            }
        }

        // step 12. 
        if (porkerNumberHist.containsValue(Long.valueOf(2))) {
            List<PokerNumber> highNumbers = porkerNumberHist.keySet().stream()
                        .filter(k -> porkerNumberHist.get(k) == 2).toList();
            List<PokerNumber> lowNumbers = sortedPokerNumbers.stream()
                        .filter(k -> porkerNumberHist.get(k) != 2).toList();
            List<PokerNumber> sortedNumbers = new ArrayList<PokerNumber>();
            sortedNumbers.addAll(
                        Arrays.asList(highNumbers.get(0), highNumbers.get(0)));
            sortedNumbers.addAll(lowNumbers);
            return new FullPokerRank(PokerRank.ONE_PAIR, sortedNumbers); 
        }

        // step 13
        return new FullPokerRank(PokerRank.HIGH_CARD, sortedPokerNumbers);
    }

    @Override
    public int compareTo(PokerHand arg0) {
        int rankDiff = this.rank.value() - arg0.rank.value();
        if (rankDiff != 0) return rankDiff;
        for (int i = 0, n = this.sortedNumbers.size(); i < n; ++i) {
            int numberDiff = this.sortedNumbers.get(i).value() 
                                - arg0.sortedNumbers.get(i).value();
            if (numberDiff != 0) { return numberDiff; }
        }
        return 0;
    }

    @Override
    public String toString() {
        return this.cards.stream()
                         .map(cards -> cards.toString())
                         .collect(Collectors.joining(" "));
    }

    public static PokerHand fromString(String s) {
        List<PokerCard> cards = Arrays.asList(s.split("\\s+")).stream()
                                      .map(PokerCard::fromString)
                                      .toList();
        return new PokerHand(cards);
    }

    private class FullPokerRank {
        public final PokerRank rank;
        public final List<PokerNumber> sortedNumbers;
        public FullPokerRank (PokerRank rank, List<PokerNumber> sortedNumbers) {
            this.rank = rank;
            this.sortedNumbers = sortedNumbers;
        } 
    }
}
