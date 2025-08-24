import java.util.Arrays;
import java.util.HashSet;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


class PigLatinTranslator {

    private static final HashSet<String> VOWELS = new HashSet<String>(
            Arrays.asList("a", "e", "i", "o", "u"));

    public String translate(String text) {
        return Arrays.asList(text.trim().split(" ")).stream()
                .map(PigLatinTranslator::translateWord)
                .collect(Collectors.joining(" "));
    }
    
    public static String translateWord(String word) {
        String subword2 = word.substring(0, 2);
        String firstChar = word.substring(0, 1);
        if (VOWELS.contains(firstChar) || subword2.equals("xr") || subword2.equals("yt")){
            return word + "ay";
        }
        if (word.charAt(0) == 'y' && VOWELS.contains(word.substring(1, 2))) {
            return word.substring(1) + "yay";
        }
        int quInd = auxFind("qu", word);
        if (quInd >= 0) {
            quInd += 2;
            return (word.length() == 2) 
                    ? (word.substring(1,2) + word.substring(0,1) + "ay")
                    : (word.substring(quInd) + word.substring(0, quInd)  + "ay");
        }
        int firstVowel = IntStream.range(0, word.length())
                                 .mapToObj(i -> word.substring(i, i + 1))
                                 .filter(s -> VOWELS.contains(s) || s.equals("y"))
                                 .mapToInt(word::indexOf).min().getAsInt();
        return word.substring(firstVowel) + word.substring(0, firstVowel) + "ay";
    }

    private static int auxFind(String pattern, String word) {
        int ind = word.indexOf(pattern);
        if (ind == -1) { return -1; }        
        boolean anyVowels = IntStream.range(0, ind)
                                    .mapToObj(i -> word.substring(i, i + 1))
                                    .anyMatch(VOWELS::contains);
        return anyVowels ? -1 : ind;
    }
}
