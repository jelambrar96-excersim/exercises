public class Twofer {
    public String twofer(String name) {
        String strname = name == null ? "you" : name;
        return "One for " + strname + ", one for me.";
    }
}
