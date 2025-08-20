import java.lang.Character;
import java.lang.String;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Anagram {

    private final List<Character> wordChars;
    private final String word;

    public Anagram(String word) {
        this.word = word.toLowerCase();
        this.wordChars = this.word.chars().sorted()
                             .mapToObj(c -> (char) c).collect(Collectors.toList());
    }

    public boolean isAnagram(String candidate) {
        if (candidate == null) { return false; }
        if (candidate.toLowerCase().equals(this.word)) { return false; }
        return candidate.toLowerCase().chars().sorted()
                        .mapToObj(c -> (char) c)
                        .collect(Collectors.toList()).equals(this.wordChars);
    }

    public List<String> match(List<String> candidates) {
        return IntStream.range(0, candidates.size())
                .filter(i -> isAnagram(candidates.get(i)))
                .mapToObj(candidates::get)
                .collect(Collectors.toList());
    }
}
