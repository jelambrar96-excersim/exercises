import java.util.LinkedList;

public class Say {

    private static final String [] ONES;
    private static final String [] TEN;
    private static final String [] BASES_NAME;
    private static final long [] BASES_NUM;
    
    static {
        ONES = new String[] {
            "zero", "one", "two", "three", "four", "five", "six",
            "seven", "eight", "nine", "ten", "eleven", "twelve",
            "thirteen", "fourteen", "fifteen", "sixteen",
            "seventeen", "eighteen", "ninteen",
        };
        TEN = new String[] {
            "", "", "twenty", "thirty", "forty", "fifty", "sixty",
            "seventy", "eighty", "ninety"
        };
        BASES_NUM = new long[] {
            1_000_000_000, 1_000_000, 1_000, 100
        };
        BASES_NAME = new String[] {
            "billion", "million", "thousand", "hundred"
        };
    }

    public String say(long number) {
        if (number < 0 || number >= 1e12) throw new IllegalArgumentException();
        if (number == 0) return "zero";
        
        LinkedList<String> parts = new LinkedList<>();
        for (int i = 0, n = BASES_NAME.length; i < n; ++i) {
            long bnum = BASES_NUM[i];
            if (number >= bnum) {
                String saynum = this.say(number / bnum);
                parts.add(saynum);
                parts.add(BASES_NAME[i]);
                number %= bnum;
            }
        }
        int intnumber = (int)number;
        
        StringBuilder sb = new StringBuilder();
        if (intnumber >= 20) {
            sb.append(TEN[intnumber / 10]);
            intnumber %= 10;
            if (intnumber > 0) sb.append("-");
        }
        if (intnumber > 0 && intnumber < 20) sb.append(ONES[intnumber]);
        parts.add(sb.toString());
        return parts.stream().reduce("", (x,y) -> x + " " + y).strip();

    }
}
