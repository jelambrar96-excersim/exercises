public class JedliksToyCar {

    private int batteryLevel = 100;
    private int distance = 0;

    public static JedliksToyCar buy() {
        return new JedliksToyCar();
    }

    public String distanceDisplay() {
        return String.format("Driven %d meters", this.distance);
    }

    public String batteryDisplay() {
        return this.batteryLevel == 0 ? "Battery empty" : String.format("Battery at %d%%", this.batteryLevel);
    }

    public void drive() {
        if (this.batteryLevel > 0) {
            this.batteryLevel -= 1;
            this.distance += 20;
        }
    }
}
