class PhoneNumber {

    final private String number;

    PhoneNumber(String numberString) {
        if (numberString == null || numberString.isEmpty()) {
            throw new IllegalArgumentException("must not be empty");
        }

        String onlyLetters = numberString.toLowerCase().replaceAll("[^a-z]","");
        if (onlyLetters.length() > 0) {
            throw new IllegalArgumentException("letters not permitted");
        }

        String onlySymbols = numberString.replaceAll("[\\s\\(\\)\\.\\+\\-]", "")
                                         .replaceAll("[a-zA-Z0-9]", "");
        System.out.println(onlySymbols);
        if (onlySymbols.length() > 0) {
            throw new IllegalArgumentException("punctuations not permitted");
        }

        String cleanedNumber = numberString.replaceAll("[^0-9]", "");
        System.out.println(cleanedNumber);
        
        if (cleanedNumber.length() < 10) {
            throw new IllegalArgumentException("must not be fewer than 10 digits");
        }
        
        if (cleanedNumber.length() == 11 && !cleanedNumber.startsWith("1")) {
            throw new IllegalArgumentException("11 digits must start with 1");
        }
        
        if (cleanedNumber.length() > 11) {
            throw new IllegalArgumentException("must not be greater than 11 digits");
        }

        String tempNumber = cleanedNumber.length() == 11 ? cleanedNumber.substring(1) : cleanedNumber;

        if (tempNumber.charAt(0) == '0') {
            throw new IllegalArgumentException("area code cannot start with zero");
        }
        if (tempNumber.charAt(0) == '1') {
            throw new IllegalArgumentException("area code cannot start with one");
        }

        if (tempNumber.charAt(3) == '0') {
            throw new IllegalArgumentException("exchange code cannot start with zero");
        }
        if (tempNumber.charAt(3) == '1') {
            throw new IllegalArgumentException("exchange code cannot start with one");
        }

        this.number = tempNumber;
    }

    String getNumber() {
        return this.number;
    }

    public static void main(String [] args) {
        PhoneNumber pn = new PhoneNumber("(023) 456-7890");
        System.out.println(pn.getNumber());
    }

}