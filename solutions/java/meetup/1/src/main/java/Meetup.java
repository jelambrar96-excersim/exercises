import java.time.DayOfWeek;
import java.time.LocalDate;

class Meetup {

    LocalDate ld = null;

    Meetup(int monthOfYear, int year) {
        this.ld = LocalDate.of(year, monthOfYear, 1);
    }

    LocalDate day(DayOfWeek dayOfWeek, MeetupSchedule schedule) {
        long deltadays = (dayOfWeek.getValue() - this.ld.getDayOfWeek().getValue() + 7) % 7;
        ld = ld.plusDays(deltadays);

        if (schedule == MeetupSchedule.FIRST)  { return ld; }
        if (schedule == MeetupSchedule.SECOND) { return ld.plusDays(7); }
        if (schedule == MeetupSchedule.THIRD)  { return ld.plusDays(14); }
        if (schedule == MeetupSchedule.FOURTH) { return ld.plusDays(21); }
        
        if (schedule == MeetupSchedule.TEENTH) {
            deltadays = ((19 - ld.getDayOfMonth()) / 7) * 7;
            return ld.plusDays(deltadays);
        }
        // MeetupSchedule.LAST
        LocalDate templd = ld.plusDays(28);
        return templd.getMonthValue() == ld.getMonthValue() ? templd : ld.plusDays(21);
    }

}