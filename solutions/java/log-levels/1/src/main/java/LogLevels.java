import java.util.HashMap;
import java.util.Map;

public class LogLevels {
    
    // private static final String [] levels = {"[ERROR]:", "[INFO]:", "[WARNING]:"};
    private static final Map<String, String> logMap = new HashMap<>();

    static {
        logMap.put("[ERROR]:", "error");
        logMap.put("[INFO]:", "info");
        logMap.put("[WARNING]:", "warning");
    }


    public static String message(String logLine) {
        String[] listKeyLogs = LogLevels.logMap.keySet().toArray(new String[0]);
        for (String kl: listKeyLogs) {
            if (logLine.contains(kl)) {
                return logLine.replace(kl, "").strip();
            }
        }
        return null;
    }

    public static String logLevel(String logLine) {
        String[] listKeyLogs = LogLevels.logMap.keySet().toArray(new String[0]);
        for (String kl: listKeyLogs) {
            if (logLine.contains(kl)) {
                return logMap.get(kl);
            }
        }
        return null;
    }

    public static String reformat(String logLine) {
        String[] listKeyLogs = LogLevels.logMap.keySet().toArray(new String[0]);
        for (String kl: listKeyLogs) {
            if (logLine.contains(kl)) {
                return logLine.replace(kl, "").strip() + " (" + LogLevels.logMap.get(kl) + ")";
            }
        }
        return null;
    }
}
