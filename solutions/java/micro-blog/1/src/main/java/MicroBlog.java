class MicroBlog {
    public String truncate(String input) {
        int[] codePoints = input.codePoints().toArray();
        int limit = Math.min(5, codePoints.length);
        return new String(codePoints, 0, limit);
    }
}
