class Bob {

    String hey(String input) {
        input = input.trim();
        int n = input.length();
        if (n == 0) { return "Fine. Be that way!"; }
        boolean haveAlpha = input.chars()
                .filter(Character::isAlphabetic).count() > 0;
        boolean isupper = input.chars()
                .filter(Character::isAlphabetic)
                .allMatch(Character::isUpperCase);
        if (input.charAt(n - 1) == '?') {
            return isupper && haveAlpha ? "Calm down, I know what I'm doing!" : "Sure.";
        }
        if (isupper && haveAlpha) { return "Whoa, chill out!"; }
        return "Whatever.";
    }

}
