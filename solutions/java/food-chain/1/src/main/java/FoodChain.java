import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class FoodChain {

    private final static List<String> verses = Arrays.asList(new String [] {
        "I know an old lady who swallowed a fly.\n" +
        "I don't know why she swallowed the fly. Perhaps she'll die.",

        "I know an old lady who swallowed a spider.\n" +
        "It wriggled and jiggled and tickled inside her.\n" +
        "She swallowed the spider to catch the fly.\n" +
        "I don't know why she swallowed the fly. Perhaps she'll die.",

        "I know an old lady who swallowed a bird.\n" +
        "How absurd to swallow a bird!\n" +
        "She swallowed the bird to catch the spider that wriggled and jiggled and tickled inside her.\n" +
        "She swallowed the spider to catch the fly.\n" +
        "I don't know why she swallowed the fly. Perhaps she'll die.",

        "I know an old lady who swallowed a cat.\n" +
        "Imagine that, to swallow a cat!\n" +
        "She swallowed the cat to catch the bird.\n" +
        "She swallowed the bird to catch the spider that wriggled and jiggled and tickled inside her.\n" +
        "She swallowed the spider to catch the fly.\n" +
        "I don't know why she swallowed the fly. Perhaps she'll die.",

        "I know an old lady who swallowed a dog.\n" +
        "What a hog, to swallow a dog!\n" +
        "She swallowed the dog to catch the cat.\n" +
        "She swallowed the cat to catch the bird.\n" +
        "She swallowed the bird to catch the spider that wriggled and jiggled and tickled inside her.\n" +
        "She swallowed the spider to catch the fly.\n" +
        "I don't know why she swallowed the fly. Perhaps she'll die.",

        "I know an old lady who swallowed a goat.\n" +
        "Just opened her throat and swallowed a goat!\n" +
        "She swallowed the goat to catch the dog.\n" +
        "She swallowed the dog to catch the cat.\n" +
        "She swallowed the cat to catch the bird.\n" +
        "She swallowed the bird to catch the spider that wriggled and jiggled and tickled inside her.\n" +
        "She swallowed the spider to catch the fly.\n" +
        "I don't know why she swallowed the fly. Perhaps she'll die.",

        "I know an old lady who swallowed a cow.\n" +
        "I don't know how she swallowed a cow!\n" +
        "She swallowed the cow to catch the goat.\n" +
        "She swallowed the goat to catch the dog.\n" +
        "She swallowed the dog to catch the cat.\n" +
        "She swallowed the cat to catch the bird.\n" +
        "She swallowed the bird to catch the spider that wriggled and jiggled and tickled inside her.\n" +
        "She swallowed the spider to catch the fly.\n" +
        "I don't know why she swallowed the fly. Perhaps she'll die.",

        "I know an old lady who swallowed a horse.\n" +
        "She's dead, of course!"
    });

    String verse(int verse) {
        if (verse < 1 || verse > FoodChain.verses.size()) {
            throw new IllegalArgumentException("Invalid verse number.");
        }
        return FoodChain.verses.get(verse - 1);
    }

    String verses(int startVerse, int endVerse) {
        if (startVerse < 1 || startVerse > FoodChain.verses.size())
            throw new IllegalArgumentException("Invalid verse startVerse.");
        if (endVerse < 1 || endVerse > FoodChain.verses.size())
            throw new IllegalArgumentException("Invalid verse endVerse.");
        if (startVerse > endVerse)
            throw new IllegalArgumentException("Invalid verse range.");

        return FoodChain.verses
                .subList(startVerse - 1, endVerse)
                .stream()
                .collect(Collectors.joining("\n\n"));
    }
}