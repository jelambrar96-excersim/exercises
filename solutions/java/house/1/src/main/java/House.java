import java.lang.String;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


class House {

    private static final List<String> VERSES = Arrays.asList(
        "This is the house that Jack built.",

        "This is the malt " +
        "that lay in the house that Jack built.",

        "This is the rat " +
        "that ate the malt " +
        "that lay in the house that Jack built.",

        "This is the cat " +
        "that killed the rat " +
        "that ate the malt " +
        "that lay in the house that Jack built.",

        "This is the dog " +
        "that worried the cat " +
        "that killed the rat " +
        "that ate the malt " +
        "that lay in the house that Jack built.",

        "This is the cow with the crumpled horn " +
        "that tossed the dog " +
        "that worried the cat " +
        "that killed the rat " +
        "that ate the malt " +
        "that lay in the house that Jack built.",

        "This is the maiden all forlorn " +
        "that milked the cow with the crumpled horn " +
        "that tossed the dog " +
        "that worried the cat " +
        "that killed the rat " +
        "that ate the malt " +
        "that lay in the house that Jack built.",

        "This is the man all tattered and torn " +
        "that kissed the maiden all forlorn " +
        "that milked the cow with the crumpled horn " +
        "that tossed the dog " +
        "that worried the cat " +
        "that killed the rat " +
        "that ate the malt " +
        "that lay in the house that Jack built.",

        "This is the priest all shaven and shorn " +
        "that married the man all tattered and torn " +
        "that kissed the maiden all forlorn " +
        "that milked the cow with the crumpled horn " +
        "that tossed the dog " +
        "that worried the cat " +
        "that killed the rat " +
        "that ate the malt " +
        "that lay in the house that Jack built.",

        "This is the rooster that crowed in the morn " +
        "that woke the priest all shaven and shorn " +
        "that married the man all tattered and torn " +
        "that kissed the maiden all forlorn " +
        "that milked the cow with the crumpled horn " +
        "that tossed the dog " +
        "that worried the cat " +
        "that killed the rat " +
        "that ate the malt " +
        "that lay in the house that Jack built.",

        "This is the farmer sowing his corn " +
        "that kept the rooster that crowed in the morn " +
        "that woke the priest all shaven and shorn " +
        "that married the man all tattered and torn " +
        "that kissed the maiden all forlorn " +
        "that milked the cow with the crumpled horn " +
        "that tossed the dog " +
        "that worried the cat " +
        "that killed the rat " +
        "that ate the malt " +
        "that lay in the house that Jack built.",

        "This is the horse and the hound and the horn " +
        "that belonged to the farmer sowing his corn " +
        "that kept the rooster that crowed in the morn " +
        "that woke the priest all shaven and shorn " +
        "that married the man all tattered and torn " +
        "that kissed the maiden all forlorn " +
        "that milked the cow with the crumpled horn " +
        "that tossed the dog " +
        "that worried the cat " +
        "that killed the rat " +
        "that ate the malt " +
        "that lay in the house that Jack built."
    );

    String verse(int verse) {
        if (verse < 1 || verse > VERSES.size()) {
            throw new IllegalArgumentException("Verse number out of range");
        }
        return VERSES.get(verse - 1);
    }

    String verses(int startVerse, int endVerse) {
        if (startVerse > endVerse) {
            throw new IllegalArgumentException("Verse range out of range");
        }
        return VERSES.subList(startVerse - 1, endVerse).stream()
                     .collect(Collectors.joining("\n"));
    }

    String sing() {
        return VERSES.stream()
                     .collect(Collectors.joining("\n"));
    }

}