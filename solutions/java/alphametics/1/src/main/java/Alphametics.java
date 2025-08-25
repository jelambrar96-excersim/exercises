import java.lang.Character;
import java.lang.String;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;



class Alphametics {

    private String pluzze; 

    Alphametics(String userInput) {
        this.pluzze = userInput.replace(" ", "");
    }

    Map<Character, Integer> solve() throws UnsolvablePuzzleException {
        
        Character[] letters = this.pluzze
                            .chars()
                            .filter(Character::isUpperCase)
                            .distinct()
                            .mapToObj(x-> (char)x)
                            .toArray(Character[]::new);

        int numberLetters = letters.length;
        if (numberLetters > 10) throw new UnsolvablePuzzleException(); 

        List<Integer> digits = IntStream.range(0, 10).boxed().collect(Collectors.toList());
        Permutations<Integer> perm = new Permutations<Integer>(digits, numberLetters);

        for(List<Integer> p: perm) {

            String tempPluzze = this.pluzze;
            Map<Character, Integer> map = new HashMap<Character, Integer>();
            for (int i = 0; i < numberLetters; ++i) {
                map.put(letters[i], p.get(i));
                tempPluzze = tempPluzze.replace(Character.toString(letters[i]), Integer.toString(p.get(i)));
            }

            String[] splitStringEquals = tempPluzze.split("==");
            if (splitStringEquals.length != 2) throw new UnsolvablePuzzleException();

            if (splitStringEquals[1].charAt(0) == '0') continue;
            long suma = Long.parseLong(splitStringEquals[1]);

            List<String> splitStringPlus = Arrays.asList(splitStringEquals[0].split("\\+"));
            
            boolean anyStartZero = splitStringPlus.stream().anyMatch(s -> (s.charAt(0) == '0'));
            if (anyStartZero) continue;

            Long sumandos = splitStringPlus.stream().mapToLong(Long::parseLong).sum();

            if (sumandos == suma) return map;
        }
        
        throw new UnsolvablePuzzleException();
    }

}