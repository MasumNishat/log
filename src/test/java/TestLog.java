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

import org.junit.jupiter.api.Test;
import org.nishat.util.log.*;

import java.io.FileWriter;
import java.io.IOException;

public class TestLog {

    @Test
    public void testAllTypes() {
        Log.i("test", LogLevel.INFO.toString());
        Log.t("test", LogLevel.TRACE.toString());
        Log.d("test", LogLevel.DEBUG.toString());
        Log.a("test", LogLevel.ALERT.toString());
        GlobalLogConfig.LOG_COLOR = true;
        Log.w("test", LogLevel.WARNING.toString());
        Log.c("test", LogLevel.CRITICAL.toString());
        Log.e("test", LogLevel.ERROR.toString());
        Log.f("test", LogLevel.FATAL.toString());

        Log.raw("test", "raw");

        LocalLogConfig config = new LocalLogConfig();
        Log.i("test", LogLevel.INFO.toString(), config);
        Log.t("test", LogLevel.TRACE.toString(), config);
        Log.d("test", LogLevel.DEBUG.toString(), config);
        Log.a("test", LogLevel.ALERT.toString(), config);
        Log.w("test", LogLevel.WARNING.toString(), config);
        Log.c("test", LogLevel.CRITICAL.toString(), config);
        Log.e("test", LogLevel.ERROR.toString(), config);
        Log.f("test", LogLevel.FATAL.toString(), config);

        Log.raw("test", "raw", config);
    }

    @Test
    public void testVisibility() {
        Log.d("test", LogLevel.DEBUG.toString()); //visible
        Log.e("test", LogLevel.ERROR.toString()); //visible
        Log.raw("test", "raw");

        //configure log
        GlobalLogConfig.LOG_LEVEL = LogLevel.WARNING;
        Log.d("test", LogLevel.DEBUG.toString()); // invisible
        Log.e("test", LogLevel.ERROR.toString()); //visible
        Log.raw("test", "raw");

        LocalLogConfig config = new LocalLogConfig();
        config.LOG_LEVEL = LogLevel.DEBUG;
        Log.t("test", LogLevel.TRACE.toString(), config); // invisible
        Log.d("test", LogLevel.DEBUG.toString(), config); //visible
        Log.a("test", LogLevel.ALERT.toString(), config); //visible
        config.LOG_LEVEL = LogLevel.CRITICAL;
        Log.w("test", LogLevel.WARNING.toString(), config); // invisible
        Log.c("test", LogLevel.CRITICAL.toString(), config); //visible
        Log.e("test", LogLevel.ERROR.toString(), config); //visible
        Log.raw("test", "raw", config);
    }

    @Test
    public void testColor() {
        Log.e("test", "error"); //default colored text

        //configure log
        GlobalLogConfig.LOG_COLOR = true;
        Log.e("test", "error"); //red colored type text

        LocalLogConfig config = new LocalLogConfig();
        config.LOG_COLOR = false;
        Log.t("test", LogLevel.TRACE.toString(), config); //default colored text
        config.LOG_COLOR = true;
        Log.e("test", LogLevel.ERROR.toString(), config); //red colored type text
    }

    @Test
    public void testFormat() {
        //default Format: "["+LogProperties.LEVEL+"] ["+LogProperties.TIMESTAMP+"] ["+LogProperties.PACKAGE+"] ["+LogProperties.INDEX+": "+LogProperties.VALUE+"] from ("+LogProperties.FILE+":"+LogProperties.LINE+")"
        Log.e("test", "error");

        //configure log
        GlobalLogConfig.FORMAT = LogProperties.INDEX+": "+LogProperties.VALUE;
        Log.e("test", "error");

        LocalLogConfig config = new LocalLogConfig();
        config.FORMAT = "["+LogProperties.TIMESTAMP+"] "+LogProperties.INDEX+": "+LogProperties.VALUE;
        Log.t("test", LogLevel.TRACE.toString(), config);
        config.FORMAT = null; //global format
        Log.e("test", LogLevel.ERROR.toString(), config);
    }

    @Test
    public void testOutput() throws IOException {
        //default new PrintWriter(System.out);
        Log.e("test", "error"); //any kind of Writer can be configured here

        //configure log
        FileWriter fw= new FileWriter("test-out.txt");
        GlobalLogConfig.WRITER = fw;
        //as we configured text file we also should set color to false
        GlobalLogConfig.LOG_COLOR = false;
        Log.e("test", "error");
        Log.e("test", "error1");
        Log.e("test", "error2");
        Log.e("test", "error3");
        fw.close();

        //configure log
        FileWriter fw1= new FileWriter("test-out.txt");
        LocalLogConfig config = new LocalLogConfig();
        config.WRITER = fw1;
        //as we configured text file we also should set color to false
        config.LOG_COLOR = false;
        Log.e("test", "error0", config);
        Log.e("test", "error1", config);
        Log.e("test", "error2", config);
        Log.e("test", "error3", config);
        fw1.close();
    }
}
