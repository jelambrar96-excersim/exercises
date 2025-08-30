import java.time.LocalTime;


class Clock {

    private LocalTime localTime;

    Clock(int hours, int minutes) {
        while (hours < 0) { hours += 24; }
        this.localTime = LocalTime.of(hours % 24, 0);
        this.add(minutes);
    }

    void add(int minutes) {
        this.localTime = this.localTime.plusMinutes(minutes);
    }

    @Override
    public String toString() {
        return this.localTime.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (this.getClass() != obj.getClass()) return false;
        Clock clock = (Clock)obj;
        return this.localTime.compareTo(clock.localTime) == 0;
    }

}