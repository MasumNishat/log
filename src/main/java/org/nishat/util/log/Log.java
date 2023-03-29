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

import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * {@link Log} is an integration of easy java logging system. It may use to display
 * different types of log in different color to debug programs easily.
 *
 * @implSpec This class is immutable and thread-safe.
 */
public final class Log {
    private static LocalLogConfig config;

    /**
     * Color string according to {@link LogLevel}
     *
     * @param str   {@link String}
     * @param level {@link LogLevel}
     * @return {@link String}
     */
    static String coloredStr(String str, LogLevel level, LocalLogConfig config) {
        if (!getConfig(boolean.class, "LOG_COLOR" , config)) return str;
        String ANSI_RESET = "\u001B[0m";
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
     * Prints a {@link LogLevel#INFO} string. Use it as follows:
     * <pre>
     *     Log.i("foo", "this is an info text");
     * </pre>
     *
     * @param tag   {@link String}
     * @param value {@link String}
     */
    public static void i(String tag, String value) {
        write(tag, value, LogLevel.INFO, null);
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
        write(tag, value, LogLevel.TRACE, null);
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
        write(tag, value, LogLevel.DEBUG, null);
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
        write(tag, value, LogLevel.ALERT, null);
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
        write(tag, value, LogLevel.WARNING, null);
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
        write(tag, value, LogLevel.CRITICAL, null);
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
        write(tag, value, LogLevel.ERROR, null);
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
        write(tag, value, LogLevel.FATAL, null);
    }


    /**
     * Prints a {@link LogLevel#INFO} string. Use it as follows:
     * <pre>
     *     LocalLogConfig config = new LocalLogConfig();
     *     config.FORMAT = LogProperties.VALUE.toString();
     *     Log.i("test", LogLevel.INFO.toString(), config);
     * </pre>
     *
     * @param tag   {@link String}
     * @param value {@link String}
     * @param config {@link LocalLogConfig}
     */
    public static void i(String tag, String value, LocalLogConfig config) {
        Objects.requireNonNull(config);
        write(tag, value, LogLevel.INFO, config);
    }

    /**
     * Prints a {@link LogLevel#TRACE} string. Use it as follows:
     * <pre>
     *     LocalLogConfig config = new LocalLogConfig();
     *     config.FORMAT = LogProperties.VALUE.toString();
     *     Log.t("test", LogLevel.TRACE.toString(), config);
     * </pre>
     *
     * @param tag   {@link String}
     * @param value {@link String}
     * @param config {@link LocalLogConfig}
     */
    public static void t(String tag, String value, LocalLogConfig config) {
        Objects.requireNonNull(config);
        write(tag, value, LogLevel.TRACE, config);
    }

    /**
     * Prints a {@link LogLevel#DEBUG} string. Use it as follows:
     * <pre>
     *     LocalLogConfig config = new LocalLogConfig();
     *     config.FORMAT = LogProperties.VALUE.toString();
     *     Log.d("test", LogLevel.DEBUG.toString(), config);
     * </pre>
     *
     * @param tag   {@link String}
     * @param value {@link String}
     * @param config {@link LocalLogConfig}
     */
    public static void d(String tag, String value, LocalLogConfig config) {
        Objects.requireNonNull(config);
        write(tag, value, LogLevel.DEBUG, config);
    }

    /**
     * Prints a {@link LogLevel#ALERT} string. Use it as follows:
     * <pre>
     *     LocalLogConfig config = new LocalLogConfig();
     *     config.FORMAT = LogProperties.VALUE.toString();
     *     Log.a("test", LogLevel.ALERT.toString(), config);
     * </pre>
     *
     * @param tag   {@link String}
     * @param value {@link String}
     * @param config {@link LocalLogConfig}
     */
    public static void a(String tag, String value, LocalLogConfig config) {
        Objects.requireNonNull(config);
        write(tag, value, LogLevel.ALERT, config);
    }

    /**
     * Prints a {@link LogLevel#WARNING} string. Use it as follows:
     * <pre>
     *     LocalLogConfig config = new LocalLogConfig();
     *     config.FORMAT = LogProperties.VALUE.toString();
     *     Log.w("test", LogLevel.WARNING.toString(), config);
     * </pre>
     *
     * @param tag   {@link String}
     * @param value {@link String}
     * @param config {@link LocalLogConfig}
     */
    public static void w(String tag, String value, LocalLogConfig config) {
        Objects.requireNonNull(config);
        write(tag, value, LogLevel.WARNING, config);
    }

    /**
     * Prints a {@link LogLevel#CRITICAL} string. Use it as follows:
     * <pre>
     *     LocalLogConfig config = new LocalLogConfig();
     *     config.FORMAT = LogProperties.VALUE.toString();
     *     Log.c("test", LogLevel.CRITICAL.toString(), config);
     * </pre>
     *
     * @param tag   {@link String}
     * @param value {@link String}
     * @param config {@link LocalLogConfig}
     */
    public static void c(String tag, String value, LocalLogConfig config) {
        Objects.requireNonNull(config);
        write(tag, value, LogLevel.CRITICAL, config);
    }

    /**
     * Prints a {@link LogLevel#ERROR} string. Use it as follows:
     * <pre>
     *     LocalLogConfig config = new LocalLogConfig();
     *     config.FORMAT = LogProperties.VALUE.toString();
     *     Log.e("test", LogLevel.ERROR.toString(), config);
     * </pre>
     *
     * @param tag   {@link String}
     * @param value {@link String}
     * @param config {@link LocalLogConfig}
     */
    public static void e(String tag, String value, LocalLogConfig config) {
        Objects.requireNonNull(config);
        write(tag, value, LogLevel.ERROR, config);
    }

    /**
     * Prints a {@link LogLevel#FATAL} string. Use it as follows:
     * <pre>
     *     LocalLogConfig config = new LocalLogConfig();
     *     config.FORMAT = LogProperties.VALUE.toString();
     *     Log.f("test", LogLevel.FATAL.toString(), config);
     * </pre>
     *
     * @param tag   {@link String}
     * @param value {@link String}
     * @param config {@link LocalLogConfig}
     */
    public static void f(String tag, String value, LocalLogConfig config) {
        Objects.requireNonNull(config);
        write(tag, value, LogLevel.FATAL, config);
    }


    /**
     * Prints a general string. Use it as follows:
     * <pre>
     *     Log.raw("foo", "this is a fatal text");
     * </pre>
     *
     * @param tag   {@link String}
     * @param value {@link String}
     */
    public static void raw(String tag, String value) {
        write(tag, value, null, null);

    }

    /**
     * Prints a general string. Use it as follows:
     * <pre>
     *     LocalLogConfig config = new LocalLogConfig();
     *     config.FORMAT = LogProperties.VALUE.toString();
     *     Log.raw("test", "raw test", config);
     * </pre>
     *
     * @param tag   {@link String}
     * @param value {@link String}
     * @param config {@link LocalLogConfig}
     */
    public static void raw(String tag, String value, LocalLogConfig config) {
        Objects.requireNonNull(config);
        write(tag, value, null, null);

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
    private static void write(String tag, String value, LogLevel level, LocalLogConfig config) {
        StackTraceElement[] st = Thread.currentThread().getStackTrace();
        if (level == null || getConfig(LogLevel.class, "LOG_LEVEL" , config).getValue() <= level.getValue())
            for (int i = (st.length - 1); i > 0; i--) {
                if (st[i].getClassName().equals(Log.class.getName())) {
                    int line = st[i+1].getLineNumber();
                    String file = st[i+1].getFileName();
                    String in = st[i+1].getClassName();
                    Map<LogProperties, String> values = new HashMap<>();
                    values.put(LogProperties.TIMESTAMP, LocalDateTime.now().toString());
                    values.put(LogProperties.PACKAGE, in);
                    values.put(LogProperties.INDEX, tag);
                    values.put(LogProperties.VALUE, value);
                    values.put(LogProperties.FILE, file);
                    values.put(LogProperties.LINE, String.valueOf(line));
                    if (level != null) {
                        values.put(LogProperties.LEVEL, coloredStr(level.name(), level, config));
                    }
                    else {
                        values.put(LogProperties.LEVEL, coloredStr(tag, LogLevel.INFO, config));
                    }
                    writeString(values, config);
                    return;
                }
            }
    }

    private static void writeString(Map<LogProperties, String> values, LocalLogConfig config) {
        String out = getConfig(String.class, "FORMAT" , config);
        for (Map.Entry<LogProperties, String> entry : values.entrySet()) {
            LogProperties i = entry.getKey();
            String v = entry.getValue();
            out = out.replaceAll(i.toString(), v);
        }
        out = out+"\n";
        try {
            getConfig(Writer.class, "WRITER" , config).write(out);
            getConfig(Writer.class, "WRITER" , config).flush();
        } catch (IOException e) {
            DebugManager.getInstance().print(e);
        }
    }

    private static <T> T getConfig (Class<T> type, String name, LocalLogConfig config) {
        try {
            Field field = GlobalLogConfig.class.getDeclaredField(name);
            if (config == null) //noinspection unchecked
                return (T) field.get(null);
            Field field1 = LocalLogConfig.class.getDeclaredField(name);

            if (field1.get(config) != null) {
                //noinspection unchecked
                return (T) field1.get(config);
            }
            //noinspection unchecked
            return (T) field.get(null);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
