public class GameMaster {

    public static String describe(Character character) {
        return "You're a level " + Integer.toString(character.getLevel()) + " " 
                + character.getCharacterClass() + " with " + Integer.toString(character.getHitPoints())
                + " hit points.";
    }

    public static String describe(Destination dst) {
        return "You've arrived at " + dst.getName() + ", which has " 
                + Integer.toString(dst.getInhabitants()) + " inhabitants.";
    }

    public static String describe(TravelMethod tm) {
        switch (tm) {
            case TravelMethod.WALKING:
                return "You're traveling to your destination by walking.";
            case TravelMethod.HORSEBACK:
                return "You're traveling to your destination on horseback.";
            default:
                throw new IllegalArgumentException();
        }
    }

    public static String describe(Character ch, Destination dst, TravelMethod tm) {
        return describe(ch) + " " + describe(tm) + " " + describe(dst);
    }

    public static String describe(Character ch, Destination dst) {
        return describe(ch, dst, TravelMethod.WALKING);
    }

}
