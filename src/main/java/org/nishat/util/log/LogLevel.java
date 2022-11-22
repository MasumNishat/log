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
/**
 * Different types of {@link LogLevel}
 */
public enum LogLevel {
    /**
     * It is white colored {@link LogLevel}
     */
    INFO(1),

    /**
     * It is green colored {@link LogLevel}
     */

    TRACE(2),

    /**
     * It is purple colored {@link LogLevel}
     */

    DEBUG(3),

    /**
     * It is cyan colored {@link LogLevel}
     */

    ALERT(4),

    /**
     * It is yellow colored {@link LogLevel}
     */

    WARNING(5),

    /**
     * It is blue colored {@link LogLevel}
     */

    CRITICAL(6),

    /**
     * It is red colored {@link LogLevel}
     */

    ERROR(7),

    /**
     * It is red colored {@link LogLevel}
     */

    FATAL(8);

    final int value;

    /**
     * @param value int
     */
    LogLevel(int value){
        this.value = value;
    }

    /**
     * Get serial number of error type
     * @return int
     */
    public int getValue(){
        return value;
    }
}
