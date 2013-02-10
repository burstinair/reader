package service;

/**
 * Created with IntelliJ IDEA.
 * User: Burst
 * Date: 13-2-9
 * Time: 下午12:19
 * To change this template use File | Settings | File Templates.
 */

import java.io.*;
import java.nio.CharBuffer;
import java.nio.charset.Charset;

public class Util {

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
}
