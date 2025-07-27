import java.lang.IllegalArgumentException;


public class FootballMatchReports {    
    public static String onField(int shirtNum) {
        if (shirtNum < 1 || shirtNum > 11) {
            throw new IllegalArgumentException();
        }
        String onfieldString = null;
        switch (shirtNum) {
            case 1:
                onfieldString = "goalie";
                break;
            case 2:
                onfieldString = "left back";
                break;
            case 3:
            case 4:
                onfieldString = "center back";
                break;
            case 5:
                onfieldString = "right back";
                break;
            case 6:
            case 7:
            case 8:
                onfieldString = "midfielder";
                break;
            case 9:
                onfieldString = "left wing";
                break;
            case 10:
                onfieldString = "striker";
                break;
            case 11:
                onfieldString = "right wing";
                break;
        }
        return onfieldString;
    }
}