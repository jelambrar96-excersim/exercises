import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.LinkedList;

public class SplitSecondStopwatch {

    private LocalTime startTime;
    private StopwatchState state;
    private LinkedList<Long> lapTimes;

    private static DateTimeFormatter formater = new DateTimeFormatterBuilder()
            .appendPattern("HH:mm:ss")
            .toFormatter();

    public SplitSecondStopwatch() {
        this.startTime = LocalTime.of(0, 0);
        this.state = StopwatchState.READY;
        lapTimes = new LinkedList<Long>();
    }

    public void start() {
        if (state == StopwatchState.RUNNING) {
            throw new IllegalStateException("cannot start an already running stopwatch");
        }
        state = StopwatchState.RUNNING;
    }

    public void stop() {
        if (state != StopwatchState.RUNNING) {
            throw new IllegalStateException("cannot stop a stopwatch that is not running");
        }
        state = StopwatchState.STOPPED;
    }

    public void reset() {
        if (state != StopwatchState.STOPPED) {
            throw new IllegalStateException("cannot reset a stopwatch that is not stopped");
        }
        startTime = LocalTime.of(0, 0, 0);
        state = StopwatchState.READY;
        lapTimes.clear();
    }

    public void lap() {
        if (state != StopwatchState.RUNNING) {
            throw new IllegalStateException("cannot lap a stopwatch that is not running");
        }
        lapTimes.add(Long.valueOf(startTime.toSecondOfDay()));
        startTime = LocalTime.of(0, 0, 0); // reset start time for next lap
    }

    public String state() {
        return state.toString();
    }

    public String currentLap() {
        return startTime.format(new DateTimeFormatterBuilder()
                .appendPattern("HH:mm:ss")
                .toFormatter());
    }

    public String total() {
        long totalSeconds = this.lapTimes.stream()
                .mapToLong(Long::longValue).sum();
        totalSeconds += startTime.toSecondOfDay();
        return LocalTime.ofSecondOfDay(totalSeconds).format(formater);
    }

    public java.util.List<String> previousLaps() {
        return lapTimes.stream()
                .map(seconds -> LocalTime.ofSecondOfDay(seconds).format(formater))
                .toList();
    }

    public void advanceTime(String timeString) {
        if (state != StopwatchState.RUNNING) {
            return;
        };
        LocalTime time = LocalTime.parse(timeString, formater);
        long seconds = time.toSecondOfDay();
        startTime = startTime.plusSeconds(seconds);
    }

    private enum StopwatchState {
        READY, RUNNING, STOPPED;

        @Override
        public String toString() {
            return name().toLowerCase();
        }
    }
}
