import java.lang.Character;
import java.lang.String;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;



class Alphametics {

    private String pluzze; 

    Alphametics(String userInput) {
        this.pluzze = userInput.toUpperCase().replace(" ", "");
    }

    Map<Character, Integer> solve() throws UnsolvablePuzzleException {
        
        List<Character> letters = this.pluzze
                                .chars()
                                .filter(Character::isAlphabetic)
                                .distinct()
                                .mapToObj(x-> (char)x)
                                .collect(Collectors.toList());

        int numberLetters = letters.size();
        if (numberLetters > 10) throw new UnsolvablePuzzleException(); 

        List<Character> fisrtLettersList = Arrays.asList(this.pluzze.replace("==", "+").split("\\+"))
                                            .stream()
                                            .map(s -> s.charAt(0))
                                            .distinct()
                                            .collect(Collectors.toList());

        List<Character> nonFirstLettersList = letters.stream()
                                                .filter(c -> !fisrtLettersList.contains(c))
                                                .collect(Collectors.toList());

        letters = new ArrayList<Character>();
        letters.addAll(nonFirstLettersList);
        letters.addAll(fisrtLettersList);

        List<Integer> allDigits = IntStream.range(0, 10)
                                           .boxed()
                                           .collect(Collectors.toList());
        Permutations<Integer> perm0 = new Permutations<Integer>(allDigits, nonFirstLettersList.size());
        
        for(List<Integer> p0: perm0) {
        
            List<Integer> noZeroDigits = IntStream.range(1, 10)
                                                  .filter(d -> !p0.contains(d))      
                                                  .boxed().collect(Collectors.toList());
            Permutations<Integer> perm1 = new Permutations<Integer>(noZeroDigits, numberLetters - nonFirstLettersList.size());

            for(List<Integer> p1: perm1) {

                List<Integer> p = new ArrayList<Integer>(p0);
                p.addAll(p1);
                // boolean anyFirstLetterZero = fisrtLettersList.stream().anyMatch(c -> (p.get(letters.indexOf(c)) == 0));
                // if (anyFirstLetterZero) continue;

                String tempPluzze = this.pluzze;
                for (int i = 0; i < numberLetters; ++i) {
                    tempPluzze = tempPluzze.replace(Character.toString(letters.get(i)), Integer.toString(p.get(i)));
                }
                
                String[] splitStringEquals = tempPluzze.split("==");
                if (splitStringEquals.length != 2) throw new UnsolvablePuzzleException();
                
                long suma = Long.parseLong(splitStringEquals[1]);
                Long sumandos = Arrays.asList(splitStringEquals[0].split("\\+"))
                .stream()
                .mapToLong(Long::parseLong)
                .sum();
                
                if (sumandos == suma) {
                    Map<Character, Integer> map = new HashMap<Character, Integer>();
                    for (int i = 0; i < numberLetters; ++i) {
                        map.put(letters.get(i), p.get(i));
                    }
                    return map;
                }

            }
        }
        
        throw new UnsolvablePuzzleException();
    }

    // public static void main(String[] args) {
    //     try {
    //         Alphametics alphametics = new Alphametics("SEND + MORE == MONEY");
    //         Map<Character, Integer> solution = alphametics.solve();
    //         System.out.println(solution);
    //     } catch (UnsolvablePuzzleException e) {
    //         System.out.println("No solution found.");
    //     }
    // }

}