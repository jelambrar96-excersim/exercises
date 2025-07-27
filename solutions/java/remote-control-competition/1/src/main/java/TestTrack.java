import java.util.List;

public class TestTrack {

    public static void race(RemoteControlCar car) {
        car.drive();
    }

    public static List<ProductionRemoteControlCar> getRankedCars(List<ProductionRemoteControlCar> cars) {
        List<ProductionRemoteControlCar> rankedCars = cars.subList(0, cars.size());
        rankedCars.sort(null);
        return rankedCars;
    }
}
