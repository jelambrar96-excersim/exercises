class RotationalCipher {

    private int shiftkey;

    RotationalCipher(int shiftKey) {
        this.shiftkey = shiftKey;
    }

    String rotate(String data) {
        return data
                .chars()
                .map(x -> RotationalCipher.rotateChar(x, this.shiftkey))
                .collect(StringBuilder::new,
                            StringBuilder::appendCodePoint,
                            StringBuilder::append)
                .toString();
    }

    private static int rotateChar(int c, int shiftKey) {
        if (Character.isUpperCase(c)) return 'A' + (c - 'A' + shiftKey) % 26;
        if (Character.isLowerCase(c)) return 'a' + (c - 'a' + shiftKey) % 26;
        return c;
    }

}
