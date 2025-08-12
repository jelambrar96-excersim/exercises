import java.util.ArrayList;
import java.util.List;

class Allergies {

    private int score;
    private static Allergen [] ALLERGEN_LIST;

    static {
        ALLERGEN_LIST = new Allergen[] {
            Allergen.EGGS, Allergen.PEANUTS, Allergen.SHELLFISH, Allergen.STRAWBERRIES,
            Allergen.TOMATOES, Allergen.CHOCOLATE, Allergen.POLLEN, Allergen.CATS
        };
    }

    Allergies(int score) {
        this.score = score;
    }

    boolean isAllergicTo(Allergen allergen) {
        return (allergen.getScore() & this.score) != 0;
    }

    List<Allergen> getList() {
        List<Allergen> allergens = new ArrayList<Allergen>(8);
        for (Allergen a: ALLERGEN_LIST) {
            if (this.isAllergicTo(a)) allergens.add(a);
        }
        return allergens;
    }
}
