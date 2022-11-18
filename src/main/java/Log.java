import java.time.LocalDateTime;

public class Log {

    
    public static String coloredStr(String str, LogLevel level) {
        String ANSI_RESET = "\u001B[0m";
        switch (level) {
            case TRACE -> {
                return Color.ANSI_GREEN + str + ANSI_RESET;
            }
            case DEBUG -> {
                return Color.ANSI_PURPLE + str + ANSI_RESET;
            }
            case INFO -> {
                return Color.ANSI_WHITE + str + ANSI_RESET;
            }
            case ALERT -> {
                return Color.ANSI_CYAN + str + ANSI_RESET;
            }
            case ERROR -> {
                return Color.ANSI_RED + str + ANSI_RESET;
            }

        }
        return Color.ANSI_WHITE + str + ANSI_RESET;
    }

    public static LogLevel LOG_LEVEL = LogLevel.INFO;

    public static void i(String tag, String value){
        if (LOG_LEVEL.getValue()<= LogLevel.INFO.getValue()) System.out.println(coloredStr(tag, LogLevel.INFO)+": "+ value);
    }

    public static void t(String tag, String value){
        if (LOG_LEVEL.getValue()<= LogLevel.TRACE.getValue()){
            int line = Thread.currentThread().getStackTrace()[2].getLineNumber();
            String file = Thread.currentThread().getStackTrace()[2].getFileName();
            String in = Thread.currentThread().getStackTrace()[2].getClassName();
            System.out.println("["+ coloredStr(LogLevel.TRACE.name(), LogLevel.TRACE)+"] ["+ LocalDateTime.now()+"] ["+in+"] ["+tag+": "+value+"] from ("+file+":"+line+")");
        }
    }

    public static void d(String tag, String value){
        if (LOG_LEVEL.getValue()<= LogLevel.DEBUG.getValue()){
            int line = Thread.currentThread().getStackTrace()[2].getLineNumber();
            String file = Thread.currentThread().getStackTrace()[2].getFileName();
            String in = Thread.currentThread().getStackTrace()[2].getClassName();
            System.out.println("["+ coloredStr(LogLevel.DEBUG.name(), LogLevel.DEBUG)+"] ["+ LocalDateTime.now()+"] ["+in+"] ["+tag+": "+value+"] from ("+file+":"+line+")");
        }
    }

    public static void a(String tag, String value){
        if (LOG_LEVEL.getValue()<= LogLevel.ALERT.getValue()){
            int line = Thread.currentThread().getStackTrace()[2].getLineNumber();
            String file = Thread.currentThread().getStackTrace()[2].getFileName();
            String in = Thread.currentThread().getStackTrace()[2].getClassName();
            System.out.println("["+ coloredStr(LogLevel.ALERT.name(), LogLevel.ALERT)+"] ["+ LocalDateTime.now()+"] ["+in+"] ["+tag+": "+value+"] from ("+file+":"+line+")");
        }
    }

    public static void w(String tag, String value){
        if (LOG_LEVEL.getValue()<= LogLevel.WARNING.getValue()){
            int line = Thread.currentThread().getStackTrace()[2].getLineNumber();
            String file = Thread.currentThread().getStackTrace()[2].getFileName();
            String in = Thread.currentThread().getStackTrace()[2].getClassName();
            System.out.println("["+ coloredStr(LogLevel.WARNING.name(), LogLevel.WARNING)+"] ["+ LocalDateTime.now()+"] ["+in+"] ["+tag+": "+value+"] from ("+file+":"+line+")");
        }
    }


    public static void c(String tag, String value){
        if (LOG_LEVEL.getValue()<= LogLevel.CRITICAL.getValue()){
            int line = Thread.currentThread().getStackTrace()[2].getLineNumber();
            String file = Thread.currentThread().getStackTrace()[2].getFileName();
            String in = Thread.currentThread().getStackTrace()[2].getClassName();
            System.out.println("["+ coloredStr(LogLevel.CRITICAL.name(), LogLevel.CRITICAL)+"] ["+ LocalDateTime.now()+"] ["+in+"] ["+tag+": "+value+"] from ("+file+":"+line+")");
        }
    }


    public static void e(String tag, String value){
        if (LOG_LEVEL.getValue()<= LogLevel.ERROR.getValue()){
            int line = Thread.currentThread().getStackTrace()[2].getLineNumber();
            String file = Thread.currentThread().getStackTrace()[2].getFileName();
            String in = Thread.currentThread().getStackTrace()[2].getClassName();
            System.out.println("["+ coloredStr(LogLevel.ERROR.name(), LogLevel.ERROR)+"] ["+ LocalDateTime.now()+"] ["+in+"] ["+tag+": "+value+"] from ("+file+":"+line+")");
        }
    }

    public static void f(String tag, String value){
        if (LOG_LEVEL.getValue()<= LogLevel.FATAL.getValue()){
            int line = Thread.currentThread().getStackTrace()[2].getLineNumber();
            String file = Thread.currentThread().getStackTrace()[2].getFileName();
            String in = Thread.currentThread().getStackTrace()[2].getClassName();
            System.out.println("["+ coloredStr(LogLevel.FATAL.name(), LogLevel.FATAL)+"] ["+ LocalDateTime.now()+"] ["+in+"] ["+tag+": "+value+"] from ("+file+":"+line+")");
        }
    }
}
