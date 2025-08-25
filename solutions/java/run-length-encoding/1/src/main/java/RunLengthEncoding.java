import java.util.regex.Pattern;

class RunLengthEncoding {

    String encode(String data) {
        /*
         * The regular expression used is "(.)\\1+", which matches one or more consecutive 
         * occurrences of the same character.
         * (.) captures a single character in the first capturing group.
         * \\1+ matches one or more occurrences of the same character captured in the first group.
         */
        return Pattern.compile("(.)\\1+")
                .matcher(data)
                .replaceAll(matchResult ->
                        Integer.toString(matchResult.group().length()) + matchResult.group(1)
                );
    }

    String decode(String data) {
        /*
         * The regular expression used is "(\\d+)(.)", which matches a sequence of digits 
         * followed by a single character.
         * (\\d+) captures one or more digits in the first capturing group.
         * (.) captures a single character in the second capturing group.
         */
        return Pattern.compile("(\\d+)(.)")
                .matcher(data)
                .replaceAll(matchResult ->
                        matchResult.group(2).repeat(Integer.parseInt(matchResult.group(1)))
                );
    }

}
