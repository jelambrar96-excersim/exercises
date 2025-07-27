class IsogramChecker {

    boolean isIsogram(String phrase) {
        long countDistincElements = phrase.toLowerCase().chars()
                                    .filter(Character::isLetter).distinct().count();
        long countAllElements = phrase.toLowerCase().chars()
                                    .filter(Character::isLetter).count();
        return countAllElements == countDistincElements;        
    }

}
