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

import java.time.LocalDateTime;

/**
 * {@link Log} is an integration of easy java logging system. It may use to display
 * different types of log in different color to debug programs easily.
 *
 * @implSpec This class is immutable and thread-safe.
 */
public class Log {

    /**
     * Color string according to {@link LogLevel}
     *
     * @param str   {@link String}
     * @param level {@link LogLevel}
     * @return {@link String}
     */
    static String coloredStr(String str, LogLevel level) {
        String ANSI_RESET = LOG_COLOR ? "\u001B[0m" : "";
        switch (level) {
            case INFO: {
                return Color.ANSI_WHITE + str + ANSI_RESET;
            }
            case TRACE: {
                return Color.ANSI_GREEN + str + ANSI_RESET;
            }
            case DEBUG: {
                return Color.ANSI_PURPLE + str + ANSI_RESET;
            }
            case ALERT: {
                return Color.ANSI_CYAN + str + ANSI_RESET;
            }
            case WARNING: {
                return Color.ANSI_YELLOW + str + ANSI_RESET;
            }
            case CRITICAL: {
                return Color.ANSI_BLUE + str + ANSI_RESET;
            }
            case ERROR:
            case FATAL: {
                return Color.ANSI_RED + str + ANSI_RESET;
            }

        }
        return Color.ANSI_WHITE + str + ANSI_RESET;
    }

    /**
     * Minimum  {@link LogLevel} of application. Configure this at very beginning of startup as follows:
     * <pre>
     *     Log.LOG_LEVEL = LogLevel.ERROR;
     * </pre>
     * Default value is {@link LogLevel#INFO}
     *
     *
     * <p>
     * Serial of log levels is
     * <ol>
     *     <li>{@link LogLevel#INFO}</li>
     *     <li>{@link LogLevel#TRACE}</li>
     *     <li>{@link LogLevel#DEBUG}</li>
     *     <li>{@link LogLevel#ALERT}</li>
     *     <li>{@link LogLevel#WARNING}</li>
     *     <li>{@link LogLevel#CRITICAL}</li>
     *     <li>{@link LogLevel#ERROR}</li>
     *     <li>{@link LogLevel#FATAL}</li>
     * </ol>
     */
    public static LogLevel LOG_LEVEL = LogLevel.INFO;

    /**
     * Set log colorful logging true or false
     */
    public static boolean LOG_COLOR = false;

    /**
     * Prints a {@link LogLevel#INFO} string. Use it as follows:
     * <pre>
     *     Log.i("foo", "this is an info text");
     * </pre>
     *
     * @param tag   {@link String}
     * @param value {@link String}
     */
    public static void i(String tag, String value) {
        write(tag, value, LogLevel.INFO);
    }

    /**
     * Prints a {@link LogLevel#TRACE} string. Use it as follows:
     * <pre>
     *     Log.t("foo", "this is a trace text");
     * </pre>
     *
     * @param tag   {@link String}
     * @param value {@link String}
     */
    public static void t(String tag, String value) {
        write(tag, value, LogLevel.TRACE);
    }

    /**
     * Prints a {@link LogLevel#DEBUG} string. Use it as follows:
     * <pre>
     *     Log.d("foo", "this is a debug text");
     * </pre>
     *
     * @param tag   {@link String}
     * @param value {@link String}
     */
    public static void d(String tag, String value) {
        write(tag, value, LogLevel.DEBUG);
    }

    /**
     * Prints a {@link LogLevel#ALERT} string. Use it as follows:
     * <pre>
     *     Log.a("foo", "this is an alert text");
     * </pre>
     *
     * @param tag   {@link String}
     * @param value {@link String}
     */
    public static void a(String tag, String value) {
        write(tag, value, LogLevel.ALERT);
    }

    /**
     * Prints a {@link LogLevel#WARNING} string. Use it as follows:
     * <pre>
     *     Log.w("foo", "this is a warning text");
     * </pre>
     *
     * @param tag   {@link String}
     * @param value {@link String}
     */
    public static void w(String tag, String value) {
        write(tag, value, LogLevel.WARNING);
    }

    /**
     * Prints a {@link LogLevel#CRITICAL} string. Use it as follows:
     * <pre>
     *     Log.c("foo", "this is a critical text");
     * </pre>
     *
     * @param tag   {@link String}
     * @param value {@link String}
     */
    public static void c(String tag, String value) {
        write(tag, value, LogLevel.CRITICAL);
    }

    /**
     * Prints a {@link LogLevel#ERROR} string. Use it as follows:
     * <pre>
     *     Log.e("foo", "this is an error text");
     * </pre>
     *
     * @param tag   {@link String}
     * @param value {@link String}
     */
    public static void e(String tag, String value) {
        write(tag, value, LogLevel.ERROR);
    }

    /**
     * Prints a {@link LogLevel#FATAL} string. Use it as follows:
     * <pre>
     *     Log.f("foo", "this is a fatal text");
     * </pre>
     *
     * @param tag   {@link String}
     * @param value {@link String}
     */
    public static void f(String tag, String value) {
        write(tag, value, LogLevel.FATAL);
    }

    /**
     * Prints a general string. Use it as follows:
     * <pre>
     *     Log.write("foo", "this is a fatal text");
     * </pre>
     *
     * @param tag   {@link String}
     * @param value {@link String}
     */
    public static void write(String tag, String value) {
        write(tag, value, null);

    }

    /**
     * Prints a dynamic {@link LogLevel} string. Use it as follows:
     * <pre>
     *     Log.write("foo", "this is a fatal text", LogLevel.FATAL);
     * </pre>
     *
     * @param tag   {@link String}
     * @param value {@link String}
     * @param level {@link LogLevel} nullable
     */
    public static void write(String tag, String value, LogLevel level) {
        StackTraceElement[] st = Thread.currentThread().getStackTrace();
        if (level == null || LOG_LEVEL.getValue() <= level.getValue())
            for (int i = 0; i < st.length; i++) {
                if (st[i].getClassName().equals(Log.class.getName())) {
                    int line = st[i+1].getLineNumber();
                    String file = st[i+1].getFileName();
                    String in = st[i+1].getClassName();
                    System.out.println("[" + (level == null ? "GENERAL" : coloredStr(level.name(), level)) + "] [" + LocalDateTime.now() + "] [" + in + "] [" + tag + ": " + value + "] from (" + file + ":" + line + ")");
                    return;
                }
            }
    }
}
