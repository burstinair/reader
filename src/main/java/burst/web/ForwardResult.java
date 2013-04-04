package burst.web;

/**
 * Created with IntelliJ IDEA.
 * User: Burst
 * Date: 13-4-4
 * Time: 下午9:32
 * To change this template use File | Settings | File Templates.
 */

import com.opensymphony.xwork2.ActionInvocation;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.dispatcher.StrutsResultSupport;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ForwardResult extends StrutsResultSupport {
    @Override
    protected void doExecute(String finalLocation, ActionInvocation invocation) throws Exception {

        HttpServletRequest request = ServletActionContext.getRequest();
        HttpServletResponse response = ServletActionContext.getResponse();

        RequestDispatcher dispatcher = request.getRequestDispatcher(finalLocation);
        dispatcher.forward(request, response);
    }
}
