import java.lang.Integer;
import java.lang.String;


import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;
import java.util.stream.Collectors;



class WordCount {

    public Map<String, Integer> phrase(String s) {        
        List<String> words = Pattern.compile("[a-zA-Z0-9]+(?:'\\w+)?")
            .matcher(s.toLowerCase())
            .results()
            .map(MatchResult::group)
            .toList();
        return words.stream()
            .collect(
                Collectors.toMap(w -> w, w -> 1, Integer::sum)
            );
    }
}
