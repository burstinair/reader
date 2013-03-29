package burst.reader.util;

/**
 * Created with IntelliJ IDEA.
 * User: Burst
 * Date: 13-2-9
 * Time: 下午12:19
 * To change this template use File | Settings | File Templates.
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.CharBuffer;
import java.nio.charset.Charset;

import javax.servlet.http.HttpServletRequest;

public class WebUtil {

    public static String readAllText(File file, Charset charset) throws FileNotFoundException, IOException
    {
        FileInputStream s = new FileInputStream(file);
        InputStreamReader r = new InputStreamReader(s, charset);
        BufferedReader br = new BufferedReader(r);
        CharBuffer cb = CharBuffer.allocate(10240);
        StringBuilder res = new StringBuilder();
        while(br.read(cb) >= 0) {
            res.append(cb.flip());
            cb.clear();
        }
        br.close();
        return res.toString();
    }

	public static String getContextURL(HttpServletRequest hsRequest) {
		return hsRequest.getRequestURI().substring(hsRequest.getContextPath().length());
	}
}
