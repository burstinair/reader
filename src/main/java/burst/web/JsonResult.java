package burst.web;

import burst.commons.model.ResultModel;
import burst.json.JsonUtils;
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
        Object result = null;
        for(Object obj : invocation.getStack().getRoot()) {
            if(obj instanceof ResultModel) {
                result = ((ResultModel)obj).getResult();
            }
        }
        PrintWriter writer = response.getWriter();
        writer.print(JsonUtils.Serialize(result));
        writer.close();
    }
}
