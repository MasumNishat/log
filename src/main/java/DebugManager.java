
import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Debug system outputs
 *
 * @author Masum Nishat
 */

public class DebugManager {
    private static DebugManager single_instance = null;

    private boolean isActive = false;

    private DebugManager()
    {
    }

    /**
     * General singleton constructor
     * @return DebugManager
     */
    public static DebugManager getInstance()
    {
        if (single_instance == null)
            single_instance = new DebugManager();
        return single_instance;
    }

    /**
     * Check if debugger is enabled
     * @return boolean
     */
    public boolean isActive() {
        return isActive;
    }

    /**
     * Enable Debugger
     */
    public void active() {
        isActive = true;
    }

    /**
     * Print String if Debugger is enabled
     */
    public void print(String str) {
        if (isActive) System.out.println(str);
    }

    /**
     * Print StackTrace of error object if Debugger is enabled
     * @param e caught error object
     */
    public void print(Exception e) {
        if (isActive) {
            System.out.println("+++++++++++++++++ Start of XMPP Debug Catch +++++++++++++++++");
            System.out.println(catchTrace(e));
            System.out.println("+++++++++++++++++ End of XMPP Debug Catch +++++++++++++++++");
        }
    }

    public void print(Throwable e) {
        if (isActive) {
            System.out.println(Log.coloredStr("+++++++++++++++++ Start of XMPP Debug Catch +++++++++++++++++", LogLevel.ERROR));
            System.out.println(catchTrace(e));
            System.out.println(Log.coloredStr("+++++++++++++++++ End of XMPP Debug Catch +++++++++++++++++", LogLevel.ERROR));
        }
    }



    /**
     * Catch trace as string, useful to send application debug report;
     * @param e caught error object
     * @return String
     */
    public String catchTrace(Exception e) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);
        return sw.toString();
    }
     public String catchTrace(Throwable e) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);
        return sw.toString();
    }
}

/*
 * if(t instanceof Exception){
 *         if(t instanceof IOException){
 *             // handle this exception type
 *         } else if (t instanceof AnotherExceptionType){
 *             //handle this one
 *         } else {
 *             // We didn't expect this Exception. What could it be? Let's log it, and let it bubble up the hierarchy.
 *         }
 *     } else if (t instanceof Error){
 *         if(t instanceof IOError){
 *             // handle this Error
 *         } else if (t instanceof AnotherError){
 *             //handle different Error
 *         } else {
 *             // We didn't expect this Error. What could it be? Let's log it, and let it bubble up the hierarchy.
 *         }
 *     } else {
 *         // This should never be reached, unless you have subclassed Throwable for your own purposes.
 *         throw t;
 *     }
 */