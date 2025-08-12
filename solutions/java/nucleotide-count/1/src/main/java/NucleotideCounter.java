import java.util.HashMap;
import java.util.Map;

class NucleotideCounter {

    private Map<Character, Integer> nucleotideCountsMap;

    NucleotideCounter(String sequence) {
        nucleotideCountsMap = new HashMap<Character, Integer>();
        nucleotideCountsMap.putAll(Map.of('A', 0, 'C', 0, 'G', 0, 'T', 0));
        for (Character c: sequence.toCharArray()) {
            if (!nucleotideCountsMap.containsKey(c)) throw new IllegalArgumentException();
            nucleotideCountsMap.replace(c, nucleotideCountsMap.get(c) + 1);
        }
    }

    Map<Character, Integer> nucleotideCounts() {
        return this.nucleotideCountsMap;
    }

}