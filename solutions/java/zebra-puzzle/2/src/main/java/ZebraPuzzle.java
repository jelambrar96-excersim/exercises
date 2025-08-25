import java.util.ArrayList;
import java.util.List;


class ZebraPuzzle {

    public final List<Color> colors;
    public final List<Nationality> nationalities;
    public final List<Drink> drinks;
    public final List<Pet> pets;
    public final List<Hobbie> hobbies;

    ZebraPuzzle() {
        // [X] 1. There are five houses.
        // [X] 2. The Englishman lives in the red house.
        // [X] 3. The Spaniard owns the dog.
        // [X] 4. The person in the green house drinks coffee.
        // [X] 5. The Ukrainian drinks tea.
        // [X] 6. The green house is immediately to the right of the ivory house.
        // [X] 7. The snail owner likes to go dancing.
        // [X] 8. The person in the yellow house is a painter.
        // [X] 9. The person in the middle house drinks milk.
        // [X] 10. The Norwegian lives in the first house.
        // [] 11. The person who enjoys reading lives in the house next to the person with the fox.
        // [] 12. The painter's house is next to the house with the horse.
        // [X] 13. The person who plays football drinks orange juice.
        // [X] 14. The Japanese person plays chess.
        // [X] 15. The Norwegian lives next to the blue house.

        for (List<Drink> drinkPerm : new Permutations<>(List.of(Drink.TEA, Drink.COFFEE, Drink.ORANGE_JUICE, Drink.WATER))) {
            // 9. Milk in the middle house
            List<Drink> drinksList = new ArrayList<>(List.of(drinkPerm.get(0), drinkPerm.get(1), Drink.MILK, drinkPerm.get(2), drinkPerm.get(3)));
            
            for(List<Nationality> natPerm : new Permutations<>(List.of(Nationality.ENGLISHMAN, Nationality.SPANIARD, Nationality.UKRAINIAN, Nationality.JAPANESE))) {
                // 10. Norwegian in the first house
                List<Nationality> nationalitiesList = new ArrayList<>(5);
                nationalitiesList.add(Nationality.NORWEGIAN); 
                nationalitiesList.addAll(natPerm);

                // 5. The Ukrainian drinks tea
                if (nationalitiesList.indexOf(Nationality.UKRAINIAN) != drinksList.indexOf(Drink.TEA)) continue;

                for (List<Color> colorPerm : new Permutations<>(List.of(Color.RED, Color.GREEN, Color.IVORY, Color.YELLOW))) {
                    
                    // 15. The Norwegian lives next to the blue house, noriwegian is in the first house, so blue is in the second house
                    List<Color> colorsList = new ArrayList<>(List.of(colorPerm.get(0), Color.BLUE, colorPerm.get(1), colorPerm.get(2), colorPerm.get(3))); 

                    // 2. The Englishman lives in the red house
                    if (nationalitiesList.indexOf(Nationality.ENGLISHMAN) != colorsList.indexOf(Color.RED)) continue;

                    // 4. The person in the green house drinks coffee
                    if (drinksList.indexOf(Drink.COFFEE) != colorsList.indexOf(Color.GREEN)) continue; 
                    
                    // 6. The green house is immediately to the right of the ivory house. 
                    // The first house is 0, and the second house is blue, so green cannot be in the first
                    // if (colorsList.indexOf(Color.GREEN) != 0) continue; 
                    if (colorsList.indexOf(Color.GREEN) - colorsList.indexOf(Color.IVORY) != 1) continue;

                    for (List<Hobbie> hobbiesList : new Permutations<>(List.of(Hobbie.PLAY_CHESS, Hobbie.READ, Hobbie.DANCING, Hobbie.PAINTER, Hobbie.PLAY_FOOTBALL))) {

                        // 8. The person in the yellow house is a painter.
                        if (hobbiesList.indexOf(Hobbie.PAINTER) != colorsList.indexOf(Color.YELLOW)) continue;

                        // 13. The person who plays football drinks orange juice.
                        if (hobbiesList.indexOf(Hobbie.PLAY_FOOTBALL) != drinksList.indexOf(Drink.ORANGE_JUICE)) continue;

                        // 14. The Japanese person plays chess.
                        if (hobbiesList.indexOf(Hobbie.PLAY_CHESS) != nationalitiesList.indexOf(Nationality.JAPANESE)) continue;

                        for (List<Pet> petsList : new Permutations<>(List.of(Pet.DOG, Pet.SNAILS, Pet.FOX, Pet.HORSE, Pet.ZEBRA))) {

                            // 3. The Spaniard owns the dog.
                            if (petsList.indexOf(Pet.DOG) != nationalitiesList.indexOf(Nationality.SPANIARD)) continue;

                            // 7. The snail owner likes to go dancing.
                            if (petsList.indexOf(Pet.SNAILS) != hobbiesList.indexOf(Hobbie.DANCING)) continue; 

                            // 11. The person who enjoys reading lives in the house next to the person with the fox.
                            if (Math.abs(hobbiesList.indexOf(Hobbie.READ) - petsList.indexOf(Pet.FOX)) != 1) continue;

                            // 12. The painter's house is next to the house with the horse.
                            if (Math.abs(hobbiesList.indexOf(Hobbie.PAINTER) - petsList.indexOf(Pet.HORSE)) != 1) continue;

                            this.colors = colorsList;
                            this.nationalities = nationalitiesList;
                            this.drinks = drinksList;
                            this.pets = petsList;
                            this.hobbies = hobbiesList;
                            return;

                        } // pets
                    } // hobbies
                } // colors
            } // nationalities
        } // drinks

        throw new IllegalStateException("No solution found");

    }

    String getWaterDrinker() {
        return this.nationalities.get(this.drinks.indexOf(Drink.WATER)).toString();
    }

    String getZebraOwner() {
        return this.nationalities.get(this.pets.indexOf(Pet.ZEBRA)).toString();
    }

    private enum Color {
        RED, GREEN, IVORY, YELLOW, BLUE;
        @Override
        public String toString() {
            return  name().toLowerCase();
        }
    }

    private enum Nationality {
        ENGLISHMAN, SPANIARD, NORWEGIAN, UKRAINIAN, JAPANESE;
        @Override
        public String toString() {
            return name().charAt(0) + name().substring(1).toLowerCase();
        }
    }

    private enum Drink {
        TEA, COFFEE, MILK, ORANGE_JUICE, WATER;
        @Override
        public String toString() {
            return name().toLowerCase().replace('_', ' ');
        }
    }

    private enum Pet {
        DOG, SNAILS, FOX, HORSE, ZEBRA;
        @Override
        public String toString() {
            return name().toLowerCase();
        }
    }

    private enum Hobbie{
        PLAY_CHESS, READ, DANCING, PAINTER, PLAY_FOOTBALL;
        @Override
        public String toString() {
            return name().toLowerCase().replace('_', ' ');
        }
    }

}

