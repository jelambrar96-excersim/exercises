import java.util.List;
import java.util.stream.IntStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;


class Yacht {

    private int score;
    private static List <YachtCategory> yachtCategories;
    
    static{
        yachtCategories = Arrays.asList(YachtCategory.ONES, YachtCategory.TWOS, 
            YachtCategory.THREES, YachtCategory.FOURS,
            YachtCategory.FIVES, YachtCategory.SIXES);
    }

    Yacht(int[] dice, YachtCategory yachtCategory) {
        
        int numDice = dice.length;
        if (numDice != 5) throw new IllegalArgumentException();

        List<Integer> histogram = new ArrayList<Integer>(Collections.nCopies(6, 0));
        int sumDices = 0;
        for (int i = 0; i < numDice; ++i) {
            sumDices += dice[i];
            int d = dice[i] - 1;
            histogram.set(d, histogram.get(d) + 1);
        }

        if (yachtCategory == YachtCategory.YACHT) 
            this.score = histogram.stream().anyMatch(x -> x == 5) ? 50 : 0;

        int yachtCategoryIndex = yachtCategories.indexOf(yachtCategory);
        if (yachtCategoryIndex >= 0)
            this.score = (yachtCategoryIndex + 1) * histogram.get(yachtCategoryIndex);

        if (yachtCategory == YachtCategory.FULL_HOUSE) {
            boolean isFullHouse = histogram.indexOf(3) >= 0 && histogram.indexOf(2) >= 0;
            this.score = isFullHouse ? sumDices : 0;
        }

        if (yachtCategory == YachtCategory.LITTLE_STRAIGHT)
            this.score = histogram.subList(0, 5).stream().allMatch(x -> x == 1) ? 30 : 0;

        if (yachtCategory == YachtCategory.BIG_STRAIGHT)
            this.score = histogram.subList(1, 6).stream().allMatch(x -> x == 1) ? 30 : 0;

        if (yachtCategory == YachtCategory.CHOICE)
            this.score = sumDices;

        if (yachtCategory == YachtCategory.FOUR_OF_A_KIND)
            this.score = IntStream.range(0, 6).filter(x -> histogram.get(x) > 3).map(x -> x + 1).sum() * 4;

    }

    int score() {
        return this.score;
    }

}
