package simulator.core.logger;

public class RaidLogger {

    private static boolean loggingEnabled = true;
    private static boolean debugLogsEnabled = false;

    public static void log(String message) {
        if (loggingEnabled) {
            System.out.println(message);
        }
    }

    public static void logTick(int tick, String message) {
        if (loggingEnabled) {
            System.out.println(String.format("%4d - %s", tick, message));
        }
    }

    public static void error(String message) {
        System.err.println(message);
    }


    public static void setLoggingEnabled(boolean loggingEnabled) {
        RaidLogger.loggingEnabled = loggingEnabled;
    }
}
