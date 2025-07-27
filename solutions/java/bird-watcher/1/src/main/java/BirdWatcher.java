
class BirdWatcher {
    private final int[] birdsPerDay;

    public BirdWatcher(int[] birdsPerDay) {
        this.birdsPerDay = birdsPerDay.clone();
    }

    public int[] getLastWeek() {
        return this.birdsPerDay;
    }

    public int getToday() {
        int lenBirdsPerDay = this.birdsPerDay.length;
        if (lenBirdsPerDay == 0) {
            return 0;
        }
        return this.birdsPerDay[lenBirdsPerDay - 1];
    }
    
    public void incrementTodaysCount() {
        int lenBirdsPerDay = this.birdsPerDay.length;
        this.birdsPerDay[lenBirdsPerDay - 1] += 1;
    }

    public boolean hasDayWithoutBirds() {
        for (int value : this.birdsPerDay) {
            if (value == 0) {
                return true;
            }
        }
        return false;
    }

    public int getCountForFirstDays(int numberOfDays) {
        int cumsum = 0;
        for (int i=0; i < numberOfDays && i < this.birdsPerDay.length; ++i) {
            cumsum += this.birdsPerDay[i];
        }
        return cumsum;
    }

    public int getBusyDays() {
        int counterBusyDays = 0;
        for (int value : this.birdsPerDay) {
            if (value >= 5) {
                counterBusyDays++;
            }
        }
        return counterBusyDays;
    }
}
