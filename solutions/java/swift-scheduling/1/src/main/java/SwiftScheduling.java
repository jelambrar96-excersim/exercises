import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class SwiftScheduling {

    public static LocalDateTime convertToDeliveryDate(LocalDateTime meetingStart, String description) {
        
        if (description == "NOW") {
            return meetingStart.plusHours(2);
        }

        if (description == "ASAP") {
            return meetingStart.getHour() < 13 
                ? LocalDateTime.of(meetingStart.toLocalDate(), LocalTime.of(17, 0))
                : LocalDateTime.of(meetingStart.toLocalDate().plusDays(1), LocalTime.of(13, 0));
        }

        if (description == "EOW") {
            DayOfWeek currentDOW = meetingStart.getDayOfWeek(); 
            boolean isEOW = currentDOW == DayOfWeek.MONDAY ||
                            currentDOW == DayOfWeek.WEDNESDAY ||
                            currentDOW == DayOfWeek.TUESDAY;
            if (isEOW) {
                int dateDelta = (DayOfWeek.FRIDAY.getValue() - currentDOW.getValue() + 7) % 7;
                return LocalDateTime.of(meetingStart.toLocalDate().plusDays(dateDelta), 
                    LocalTime.of(17, 0));
            }
            isEOW = currentDOW == DayOfWeek.THURSDAY ||
                    currentDOW == DayOfWeek.FRIDAY;
            if (isEOW) {
                int dateDelta = (DayOfWeek.SUNDAY.getValue() - currentDOW.getValue() + 7) % 7;
                return LocalDateTime.of(meetingStart.toLocalDate().plusDays(dateDelta), 
                    LocalTime.of(20, 0));
            }
        }

        Pattern patron1 = Pattern.compile("^\\d+M");
        Matcher matcher1 = patron1.matcher(description);
        if (matcher1.find()) {
            int nMonth = Integer.parseInt(description.replace("M", ""));
            LocalDate date = meetingStart.getMonth().getValue() < nMonth 
                ? meetingStart.toLocalDate().withMonth(nMonth).withDayOfMonth(1)
                : meetingStart.toLocalDate().plusYears(1).withMonth(nMonth).withDayOfMonth(1); 
            while (true) {
                DayOfWeek weekday = date.getDayOfWeek();
                boolean cond = (weekday != DayOfWeek.SUNDAY) && (weekday != DayOfWeek.SATURDAY);
                if (cond) { break; }
                date = date.plusDays(1);
            }
            return LocalDateTime.of(date, LocalTime.of(8, 0));
        }

        Pattern patron2 = Pattern.compile("^Q\\d+");
        Matcher matcher2 = patron2.matcher(description);
        if (matcher2.find()) {
            int quater = Integer.parseInt(description.replace("Q", ""));
            int month = ((quater) * 3) % 12 + 1;
            LocalDate date = meetingStart.toLocalDate().withMonth(month).withDayOfMonth(1);
            if (date.isBefore(meetingStart.toLocalDate())) {
                date = date.plusYears(1);
            }
            while (true) {
                date = date.plusDays(-1);
                DayOfWeek weekday = date.getDayOfWeek();
                boolean cond = (weekday != DayOfWeek.SUNDAY) && (weekday != DayOfWeek.SATURDAY);
                if (cond) { break; }
            }
            return LocalDateTime.of(date, LocalTime.of(8, 0));
        }
        
        return null;
    }
}
