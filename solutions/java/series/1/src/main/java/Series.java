import java.lang.String;
import java.util.List;
import java.util.stream.IntStream;


class Series {

    private final String s;

    Series(String string) {
        if (string.isEmpty()) 
            throw new IllegalArgumentException("series cannot be empty");
        this.s = string;
    }

    List<String> slices(int num) {
        int len = this.s.length();
        if (num < 1) 
            throw new IllegalArgumentException("slice length cannot be negative or zero");
        if (len < num) 
            throw new IllegalArgumentException("slice length cannot be greater than series length");
        return IntStream.range(0, len - num + 1)
                        .mapToObj(x-> this.s.substring(x, x + num))
                        .map(String::valueOf)
                        .toList();
    }
}
