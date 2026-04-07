class Atbash {

    String encode(String input) {
        return input.toLowerCase()
                    .chars()
                    .filter(x -> Character.isAlphabetic(x) || Character.isDigit(x))
                    .map(x-> Character.isDigit(x) ? x: 219 - x)
                    .collect(StringBuilder::new, (builder, element) -> {
                        if (builder.length() % 6 == 5) {
                            builder.append(" ");
                        }
                        builder.append((char) element);
                    }, StringBuilder::append)
                    .toString();
    }

    String decode(String input) {
        return input.toLowerCase()
                    .chars()
                    .filter(x -> Character.isAlphabetic(x) || Character.isDigit(x))
                    .map(x-> Character.isDigit(x) ? x: 219 - x)
                    .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                    .toString();
    }

}
