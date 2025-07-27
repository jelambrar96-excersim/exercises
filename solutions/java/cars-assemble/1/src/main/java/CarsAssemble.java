public class CarsAssemble {

    public double productionRatePerHour(int speed) {
        double successRate = 0;
        if (speed <= 4) {
            successRate = 1.00;
        } 
        else if(speed <= 8) {
            successRate = 0.90;
        }
        else if (speed == 9) {
            successRate = 0.80;
        }
        else if (speed == 10) {
            successRate = 0.77;
        }
        return speed * 221 * successRate;
    }

    public int workingItemsPerMinute(int speed) {
        return (int) (productionRatePerHour(speed) / 60);
    }
}
