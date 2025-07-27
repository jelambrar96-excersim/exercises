class Fighter {

    boolean isVulnerable() {
        return true;
    }

    int getDamagePoints(Fighter fighter) {
        return 1;
    }
}


class Warrior extends Fighter {

    public String toString() {
        return "Fighter is a Warrior";
    }

    public boolean isVulnerable() {
        return false;
    }

    public int getDamagePoints(Fighter fighter) {
        return fighter.isVulnerable() ? 10 : 6;
    }

}


class Wizard extends Fighter {

    public boolean isVulnerable = true;

    public String toString() {
        return "Fighter is a Wizard";
    }

    public void prepareSpell() {
        this.isVulnerable = false;
    }

    public boolean isVulnerable() {
        return this.isVulnerable;
    }

    public int getDamagePoints(Fighter fighter) {
        return this.isVulnerable ? 3 : 12;
    }

}