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

    @Override
    public boolean isVulnerable() {
        return false;
    }

    @Override
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

    @Override
    public boolean isVulnerable() {
        return this.isVulnerable;
    }

    @Override
    public int getDamagePoints(Fighter fighter) {
        return this.isVulnerable ? 3 : 12;
    }

}