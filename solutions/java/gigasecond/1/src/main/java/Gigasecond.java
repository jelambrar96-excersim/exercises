import java.time.LocalDate;
import java.time.LocalDateTime;

public class Gigasecond {

    LocalDateTime localDT;

    public Gigasecond(LocalDate moment) {
        this.localDT = moment.atStartOfDay().plusSeconds(1_000_000_000);
    }

    public Gigasecond(LocalDateTime moment) {
        this.localDT = moment.plusSeconds(1_000_000_000);
    }

    public LocalDateTime getDateTime() {
        return this.localDT;
    }
}
