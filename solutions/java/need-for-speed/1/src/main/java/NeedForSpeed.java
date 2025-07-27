class NeedForSpeed {

    private int speed;
    private int batteryLevel;
    private int batteryDrain;
    private int distance;

    NeedForSpeed(int speed, int batteryDrain) {
        this.speed = speed;
        this.batteryDrain = batteryDrain;
        this.distance = 0;
        this.batteryLevel = 100;
    }

    public boolean batteryDrained() {
        return this.batteryLevel < batteryDrain;
    }

    public int distanceDriven() {
        return this.distance;
    }

    public void drive() {
        if (!this.batteryDrained()) {
            this.batteryLevel -= this.batteryDrain;
            this.distance += this.speed;
        }
    }

    public static NeedForSpeed nitro() {
        return new NeedForSpeed(50, 4);
    }
}

class RaceTrack {

    private int distance;

    RaceTrack(int distance) {
        this.distance = distance;
    }

    public boolean canFinishRace(NeedForSpeed car) {
        while (distance > car.distanceDriven()) {
            if (car.batteryDrained()) {
                return false;
            }
            car.drive();
        }
        return true;
    }
}
