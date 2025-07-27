import java.lang.Comparable;

class ProductionRemoteControlCar implements RemoteControlCar, Comparable<ProductionRemoteControlCar>{

    private int distance = 0;
    private int numberOfVictories = 0;

    public void drive() {
        this.distance += 10;
    }

    public int getDistanceTravelled() {
        return this.distance;
    }

    public int getNumberOfVictories() {
        return this.numberOfVictories;
    }

    public void setNumberOfVictories(int numberOfVictories) {
        this.numberOfVictories += numberOfVictories;
    }

    public int compareTo(ProductionRemoteControlCar car) {
        return car.getNumberOfVictories() - this.getNumberOfVictories();
    }
}
