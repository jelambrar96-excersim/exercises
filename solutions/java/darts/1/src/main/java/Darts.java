class Darts {
    int score(double xOfDart, double yOfDart) {
        double distanceSquared = xOfDart * xOfDart + yOfDart * yOfDart;
        if (distanceSquared <= 1.0) return 10;
        if (distanceSquared <= 25.0) return 5;
        if (distanceSquared <= 100.0) return 1;
        return 0;
    }
}
