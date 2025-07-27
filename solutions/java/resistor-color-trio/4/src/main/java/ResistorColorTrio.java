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

        if (resistence >= 1e9) {
            resistence /= 1e9;
            String formatdecimal = resistence % 1 == 0 ? "%.0f" : "%.1f"; 
            return String.format(formatdecimal + " gigaohms", resistence);
        }
        if (resistence >= 1e6) {
            resistence /= 1e6;
            String formatdecimal = resistence % 1 == 0 ? "%.0f" : "%.1f"; 
            return String.format(formatdecimal + " megaohms", resistence);
        }
        else if ( resistence >= 1e3) {
            resistence /= 1e3;
            String formatdecimal = resistence % 1 == 0 ? "%.0f" : "%.1f"; 
            return String.format(formatdecimal + " kiloohms", resistence);
        }
        return String.format("%.0f ohms", resistence);
    }

}
