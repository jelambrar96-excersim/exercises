public class LogLine {

    String logLine;

    public LogLine(String logLine) {
        this.logLine = logLine;
    }

    public LogLevel getLogLevel() {
        String logLevelString = this.logLine.substring(1, 4);
        switch (logLevelString) {
            case "TRC":
                return LogLevel.TRACE;
            case "DBG":
                return LogLevel.DEBUG;        
            case "INF":
                return LogLevel.INFO;        
            case "WRN":
                return LogLevel.WARNING;        
            case "ERR":
                return LogLevel.ERROR;        
            case "FTL":
                return LogLevel.FATAL;        
            default:
                return LogLevel.UNKNOWN;
        }
    }

    public String getOutputForShortLog() {
        int idLogLevel = this.getLogLevel().getIdLogLevel();
        String message = this.logLine.substring(7);
        return String.format("%d:%s", idLogLevel, message);
    }
}
