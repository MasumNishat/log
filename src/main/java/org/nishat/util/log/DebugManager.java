/*
 * Copyright 2022 Al Masum Nishat (http://nishat.org)
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and
 * associated documentation files (the "Software"), to deal in the Software without restriction, including
 * without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies
 * of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following
 * conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED,
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR
 * PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
 * LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT
 * OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
 * OTHER DEALINGS IN THE SOFTWARE.
 *
 */
package org.nishat.util.log;
import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * {@link DebugManager} is an integration of easy java logging system. It may use to display
 * different types of {@link Throwable} errors to debug programs easily.
 *
 * @implSpec
 * This class is immutable and thread-safe.
 */
public class DebugManager {
    private static DebugManager single_instance = null;
    private boolean isActive = false;

    private DebugManager() {
        throw new RuntimeException("Making instance is not permitted");
    }

    /**
     * General singleton constructor
     * @return {@link DebugManager}
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
     * Disable Debugger
     */
    public void deActive() {
        isActive = false;
    }

    /**
     * Print String if Debugger is enabled
     *
     * @param str {@link String}
     */
    public void print(String str) {
        if (isActive) System.out.println(str);
    }

    /**
     * Print StackTrace of error object if Debugger is enabled
     * @param e {@link Exception} error object
     */
    public void print(Exception e) {
        if (isActive) {
            System.out.println("+++++++++++++++++ Start of Debug Catch +++++++++++++++++");
            System.out.println(catchTrace(e));
            System.out.println("+++++++++++++++++ End of Debug Catch +++++++++++++++++");
        }
    }

    /**
     * Print StackTrace of error object if Debugger is enabled
     * @param e {@link Throwable} error object
     */

    public void print(Throwable e) {
        if (isActive) {
            System.out.println(Log.coloredStr("+++++++++++++++++ Start of Debug Catch +++++++++++++++++", LogLevel.ERROR));
            System.out.println(catchTrace(e));
            System.out.println(Log.coloredStr("+++++++++++++++++ End of Debug Catch +++++++++++++++++", LogLevel.ERROR));
        }
    }

    /**
     * Catch trace as string, useful to send application debug report;
     * @param e {@link Exception} error object
     * @return String
     */
    public String catchTrace(Exception e) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);
        return sw.toString();
    }

    /**
     * Catch trace as string, useful to send application debug report;
     * @param e {@link Throwable} error object
     * @return String
     */
    public String catchTrace(Throwable e) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);
        return sw.toString();
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
}



