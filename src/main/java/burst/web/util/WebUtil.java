package burst.web.util;

/**
 * Created with IntelliJ IDEA.
 * User: Burst
 * Date: 13-2-9
 * Time: 下午12:19
 * To change this template use File | Settings | File Templates.
 */

import burst.web.model.RemoteModel;
import info.monitorenter.cpdetector.io.ASCIIDetector;
import info.monitorenter.cpdetector.io.CodepageDetectorProxy;
import info.monitorenter.cpdetector.io.JChardetFacade;
import info.monitorenter.cpdetector.io.UnicodeDetector;
import org.apache.struts2.ServletActionContext;

import java.io.*;
import java.nio.CharBuffer;
import java.nio.charset.Charset;

import javax.servlet.http.HttpServletRequest;

public class WebUtil {

    public static final String HEAD_USERAGENT = "User-Agent";

    public static final CodepageDetectorProxy cpDetector;
    static {
        cpDetector = CodepageDetectorProxy.getInstance();
        //cpDetector.add(ASCIIDetector.getInstance());
        cpDetector.add(UnicodeDetector.getInstance());
        cpDetector.add(JChardetFacade.getInstance());
    }

    static final int BUFFER_SIZE = 10240;
    static final int DETECT_LENGTH = 10000;

    public static Charset detect(File file) throws IOException {
        return detect(new BufferedInputStream(new FileInputStream(file)));
    }

    public static Charset detect(BufferedInputStream bis) throws IOException {
        return cpDetector.detectCodepage(bis, DETECT_LENGTH);
    }

    public static String readAllText(InputStream is) throws IOException {

        InputStream s = new BufferedInputStream(is);
        Charset charset = cpDetector.detectCodepage(s, DETECT_LENGTH);
        InputStreamReader r = new InputStreamReader(s, charset);
        BufferedReader br = new BufferedReader(r);
        CharBuffer cb = CharBuffer.allocate(BUFFER_SIZE);
        StringBuilder res = new StringBuilder();
        while(br.read(cb) >= 0) {
            res.append(cb.flip());
            cb.clear();
        }
        br.close();
        return res.toString();
    }

    public static String readAllText(File file) throws IOException {
        return readAllText(new FileInputStream(file));
    }

	public static String getContextURL(HttpServletRequest hsRequest) {
		return hsRequest.getRequestURI().substring(hsRequest.getContextPath().length());
	}

    public static String EncodeCRLF(String content) {
        StringBuilder res = new StringBuilder();
        for (int i = 0, l = content.length(); i < l; ++i) {
            char c = content.charAt(i);
            if (c == '\r') {
                if(i < l - 1) {
                    if (content.charAt(i + 1) == '\n') {
                        ++i;
                    }
                }
                res.append("<br />");
            } else if (c == '\n') {
                res.append("<br />");
            } else if (c == ' ') {
                res.append("&nbsp;");
            } else if (c == '<') {
                res.append("&lt;");
            } else if (c == '>') {
                res.append("&gt;");
            } else if (c == '&') {
                res.append("&amp;");
            } else {
                res.append(c);
            }
        }
        return res.toString();
    }

    public static RemoteModel getRemoteModel() {
        RemoteModel res = new RemoteModel();

        HttpServletRequest request = ServletActionContext.getRequest();
        res.setRemoteAddr(request.getRemoteAddr());
        res.setUserAgent(request.getHeader(HEAD_USERAGENT));

        return res;
    }
}
