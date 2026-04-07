import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Ledger {
    public LedgerEntry createLedgerEntry(String d, String desc, int c) {
        LedgerEntry le = new LedgerEntry();
        le.setChange(c);
        le.setDescription(desc);
        le.setLocalDate(LocalDate.parse(d));
        return le;
    }

    public String format(String cur, String loc, LedgerEntry[] entries) {
        if (!cur.equals("USD") && !cur.equals("EUR")) {
            throw new IllegalArgumentException("Invalid currency");
        }
        if (!loc.equals("en-US") && !loc.equals("nl-NL")) {
            throw new IllegalArgumentException("Invalid locale");
        }

        String header = loc.equals("en-US") ? "Date       | Description               | Change       "
                                            : "Datum      | Omschrijving              | Verandering  ";
        StringBuilder sBuilder = new StringBuilder();
        sBuilder.append(header);
        if (entries.length == 0) { return sBuilder.toString(); }
        
        String curSymb = cur.equals("USD") ? "$" : "€";
        String datPat = loc.equals("en-US") ? "MM/dd/yyyy" : "dd/MM/yyyy";
        
        DecimalFormatSymbols decimalFormatSymbols = DecimalFormatSymbols.getInstance();
        decimalFormatSymbols.setDecimalSeparator(loc.equals("en-US") ? '.' : ',');
        decimalFormatSymbols.setGroupingSeparator(loc.equals("en-US") ? ',' : '.');
        
        List<LedgerEntry> all = Arrays.asList(entries);
        all.sort((o1, o2) -> {
            if ((o1.getChange() < 0 && o2.getChange() < 0) || (o1.getChange() >= 0 && o2.getChange() >= 0)) {
                return o1.getLocalDate().compareTo(o2.getLocalDate());
            }
            return o1.getChange() >= 0 ? 1 : -1;
        });

        for (LedgerEntry e: all) {

            String date = e.getLocalDate().format(DateTimeFormatter.ofPattern(datPat));

            String desc = e.getDescription();
            if (desc.length() > 25) {
                desc = desc.substring(0, 22);
                desc = desc + "...";
            }

            String converted = new DecimalFormat("#,##0.00", decimalFormatSymbols)
                                    .format(java.lang.Math.abs(e.getChange() / 100.0));


            String amount = (e.getChange() < 0 && loc.equals("en-US") ? "(" : "")
                            + (e.getChange() < 0 && loc.equals("nl-NL") ? curSymb + " -" : curSymb) 
                            + (e.getChange() >= 0 && loc.equals("nl-NL") ? " ": "") 
                            + converted 
                            + (e.getChange() < 0 && loc.equals("en-US") ? ")" : " ");            

            sBuilder.append("\n");
            sBuilder.append(String.format("%s | %-25s | %13s",
                date,
                desc,
                amount));
        }

        return sBuilder.toString();
    }

    public static class LedgerEntry {
        LocalDate localDate;
        String description;
        double change;

        public LocalDate getLocalDate() {
            return localDate;
        }

        public void setLocalDate(LocalDate localDate) {
            this.localDate = localDate;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public double getChange() {
            return change;
        }

        public void setChange(double change) {
            this.change = change;
        }
    }

}
