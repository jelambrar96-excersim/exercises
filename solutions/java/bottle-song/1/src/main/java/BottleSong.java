import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class BottleSong {

    private List<String> verses = Arrays.asList(new String [] {
        "Ten green bottles hanging on the wall,\n" +
        "Ten green bottles hanging on the wall,\n" +
        "And if one green bottle should accidentally fall,\n" +
        "There'll be nine green bottles hanging on the wall.\n",

        "Nine green bottles hanging on the wall,\n" +
        "Nine green bottles hanging on the wall,\n" +
        "And if one green bottle should accidentally fall,\n" +
        "There'll be eight green bottles hanging on the wall.\n",

        "Eight green bottles hanging on the wall,\n" +
        "Eight green bottles hanging on the wall,\n" +
        "And if one green bottle should accidentally fall,\n" +
        "There'll be seven green bottles hanging on the wall.\n",

        "Seven green bottles hanging on the wall,\n" +
        "Seven green bottles hanging on the wall,\n" +
        "And if one green bottle should accidentally fall,\n" +
        "There'll be six green bottles hanging on the wall.\n",

        "Six green bottles hanging on the wall,\n" +
        "Six green bottles hanging on the wall,\n" +
        "And if one green bottle should accidentally fall,\n" +
        "There'll be five green bottles hanging on the wall.\n",

        "Five green bottles hanging on the wall,\n" +
        "Five green bottles hanging on the wall,\n" +
        "And if one green bottle should accidentally fall,\n" +
        "There'll be four green bottles hanging on the wall.\n",

        "Four green bottles hanging on the wall,\n" +
        "Four green bottles hanging on the wall,\n" +
        "And if one green bottle should accidentally fall,\n" +
        "There'll be three green bottles hanging on the wall.\n",

        "Three green bottles hanging on the wall,\n" +
        "Three green bottles hanging on the wall,\n" +
        "And if one green bottle should accidentally fall,\n" +
        "There'll be two green bottles hanging on the wall.\n",

        "Two green bottles hanging on the wall,\n" +
        "Two green bottles hanging on the wall,\n" +
        "And if one green bottle should accidentally fall,\n" +
        "There'll be one green bottle hanging on the wall.\n",

        "One green bottle hanging on the wall,\n" +
        "One green bottle hanging on the wall,\n" +
        "And if one green bottle should accidentally fall,\n" +
        "There'll be no green bottles hanging on the wall.\n"
    });

    String recite(int startBottles, int takeDown) {
        int ind = 10 - startBottles;
        return verses
                .subList(ind, ind + takeDown)
                .stream()
                .collect(Collectors.joining("\n"));
    }

}