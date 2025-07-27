class ResistorColorTrio {

    int colorCode(String color) {
        String colorLower = color.toLowerCase();
        switch (colorLower) {
            case "black":
                return 0;
            case "brown":
                return 1;
            case "red":
                return 2;
            case "orange":
                return 3;
            case "yellow":
                return 4;
            case "green":
                return 5;
            case "blue":
                return 6;
            case "violet":
                return 7;
            case "grey":
                return 8;
            case "white":
                return 9;
            default:
                throw new IllegalArgumentException("Unknown color: " + color);
        }
    }
    
    int value(String[] colors) {
        if (colors.length < 2) {
            throw new IllegalArgumentException("Invalid number of arguments.");
        }
        return colorCode(colors[0]) * 10 + colorCode(colors[1]);
    }

    String label(String[] colors) {
        if (colors.length < 3) {
            throw new IllegalArgumentException("Invalid number of arguments.");
        }
        int value = value(colors);
        int zeros = colorCode(colors[2]);
        double resistence = value * Math.pow(10, zeros);

        String []labels = new String[] {" ohms", " kiloohms", " megaohms", " gigaohms"};
        int indexLabel = (int)Math.floor(Math.log10(resistence) / 3);
        if (indexLabel > 0) {
            resistence /= Math.pow(10, indexLabel * 3);
            String formatdecimal = resistence % 1 == 0 ? "%.0f" : "%.1f"; 
            return String.format(formatdecimal + labels[indexLabel], resistence);
        }
        return String.format("%.0f ohms", resistence);
    }

}
