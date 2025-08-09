import java.lang.String;

import java.util.HashSet;
import java.util.Random;


class Robot {

    private static HashSet<String> robotNames = new HashSet<>();
    private HashSet<String> usedNames = new HashSet<>();
    private String name;

    Robot() {
        do {
            this.name = generateName();
        } while (robotNames.contains(this.name));
        robotNames.add(this.name);
        usedNames.add(this.name);
    }

    private static String generateName() {
        Random random = new Random();
        StringBuilder name = new StringBuilder();
        name.append((char) ('A' + random.nextInt(26))); // First letter A-Z
        name.append((char) ('A' + random.nextInt(26))); // Second letter A-Z
        for (int i = 0; i < 3; i++) {
            name.append(random.nextInt(10)); // Three digits 0-9
        }
        return name.toString();
    }

    String getName() {
        return this.name;
    }

    void reset() {
        robotNames.remove(this.name);
        do {
            this.name = generateName();
        } while (robotNames.contains(this.name) || usedNames.contains(this.name));
        robotNames.add(this.name);
        usedNames.add(this.name);
    }

}
