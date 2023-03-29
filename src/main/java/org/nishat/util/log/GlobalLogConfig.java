/*
 * Copyright 2023 Al Masum Nishat (http://nishat.org)
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
import java.io.Writer;

public class GlobalLogConfig {
    public static Writer WRITER = new PrintWriter(System.out);
    public static String FORMAT = "["+LogProperties.LEVEL+"] ["+LogProperties.TIMESTAMP+"] ["+LogProperties.PACKAGE+"] ["+LogProperties.INDEX+": "+LogProperties.VALUE+"] from ("+LogProperties.FILE+":"+LogProperties.LINE+")";
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
}
