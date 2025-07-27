import java.util.LinkedList;
import java.util.List;
import java.util.stream.IntStream;

public class KillerSudokuHelper {


    List<List<Integer>> combinationsInCage(Integer cageSum, Integer cageSize, List<Integer> exclude) {
        
        List<Integer> validNumbers = exclude != null ? 
            IntStream.range(1, 10).filter(n -> !exclude.contains(n)).boxed().toList() :
            IntStream.range(1, 10).boxed().toList();
        
        List<List<Integer>> solutions = new LinkedList<List<Integer>>();
        SortedPermutations<Integer> permutations = new SortedPermutations<Integer>(validNumbers, cageSize);

        for (List<Integer> p: permutations) {
            if (p.stream().mapToInt(Integer::valueOf).sum() == cageSum) solutions.add(p);
        }

        return solutions;
    }

    List<List<Integer>> combinationsInCage(Integer cageSum, Integer cageSize) {
        return this.combinationsInCage(cageSum, cageSize, null);
    }

}
