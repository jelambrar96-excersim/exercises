import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

class DnDCharacter {

    int strength, dexterity, constitution, intelligence, wisdom, charisma, hitpoints;

    DnDCharacter() {
        this.strength = ability(rollDice());
        this.dexterity = ability(rollDice());
        this.intelligence = ability(rollDice());
        this.wisdom = ability(rollDice());
        this.charisma = ability(rollDice());
        this.constitution = ability(rollDice());
        this.hitpoints = 10 + modifier(this.constitution);
    }

    int ability(List<Integer> scores) {
        return scores.stream().sorted(Comparator.reverseOrder()).limit(3).reduce(0, (a,b) -> a+b);
    }

    List<Integer> rollDice() {
        List<Integer> out = new ArrayList<Integer>(4);
        Random rg = new Random();
        for (int i = 0; i < 4; ++i) {
            out.add(i, 1 + rg.nextInt(6));
        }
        return out;
    }

    int modifier(int input) {
        return (int)Math.floor(((double)input - 10)/2);
    }

    int getStrength() {
        return this.strength;
    }

    int getDexterity() {
        return this.dexterity;
    }

    int getConstitution() {
        return this.constitution;
    }

    int getIntelligence() {
        return this.intelligence;
    }

    int getWisdom() {
        return this.wisdom;
    }

    int getCharisma() {
        return this.charisma;
    }

    int getHitpoints() {
        return this.hitpoints;
    }

}

