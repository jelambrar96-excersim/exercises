import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

class KindergartenGarden {

    public final static String [] students;
    static {
        students = new String[] {
            "Alice", "Bob", "Charlie", "David", "Eve", "Fred",
            "Ginny", "Harriet", "Ileana", "Joseph", "Kincaid", "Larry"
        };
    }

    private HashMap<String, List<Plant>> mapGarden;

    KindergartenGarden(String garden) {

        String[] gardenRows = garden.split("\n");
        if (gardenRows.length != 2) throw new IllegalArgumentException("Input must be separated by \\n");
        if (gardenRows[0].length() != gardenRows[1].length()) throw new IllegalArgumentException("Input must be two equal-length");

        this.mapGarden = new HashMap<String, List<Plant>>();
        for (int i = 0, n = gardenRows[1].length(); i < n; i+=2) {
            List<Plant> group = new ArrayList<Plant>(4);
            for (int j = 0; j < 2; ++j) {
                group.add(Plant.getPlant(gardenRows[j].charAt(i)));
                group.add(Plant.getPlant(gardenRows[j].charAt(i + 1)));
            }
            this.mapGarden.put(students[i/2], group);
        }
    }

    List<Plant> getPlantsOfStudent(String student) {
        return this.mapGarden.get(student);
    }

}
