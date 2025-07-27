class Proverb {

    private String proverb;

    Proverb(String[] words) {

        StringBuilder sb = new StringBuilder();
        for (int i = 0, n = words.length - 1; i < n; ++i) {
            sb.append("For want of a " + words[i] + " the " + words[i + 1] + " was lost.\n");
        }
        if (words.length > 0) {
            sb.append("And all for the want of a " + words[0] + ".");
        }
        this.proverb = sb.toString();
    }

    String recite() {
        return this.proverb;
    }

}
