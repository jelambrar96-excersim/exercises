import java.lang.String;

class Acronym {

    String acronym;

    Acronym(String phrase) {
        this.acronym = phrase
            .replaceAll("'", "")
            .replaceAll("[ ,_-]+", " ")
            .replaceAll("(\\B[a-zA-Z]*)? *", "")
            .toUpperCase();
    }

    String get() {
        return this.acronym;
    }

}
