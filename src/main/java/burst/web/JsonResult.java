package burst.web;

import com.opensymphony.xwork2.ActionInvocation;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.dispatcher.StrutsResultSupport;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

import static javax.servlet.http.HttpServletResponse.SC_FOUND;

/**
 * Created with IntelliJ IDEA.
 * User: Burst
 * Date: 13-4-4
 * Time: 下午9:43
 * To change this template use File | Settings | File Templates.
 */
public class JsonResult extends StrutsResultSupport {

    @Override
    protected void doExecute(String finalLocation, ActionInvocation invocation) throws Exception {

        HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType("application/json");
        PrintWriter writer = response.getWriter();
        writer.print(finalLocation);
        writer.close();
    }
}
