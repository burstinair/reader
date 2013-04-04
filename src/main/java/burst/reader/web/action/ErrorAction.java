package burst.reader.web.action;

import burst.reader.web.action.model.ErrorActionModel;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * Created with IntelliJ IDEA.
 * User: Burst
 * Date: 13-4-5
 * Time: 上午12:50
 * To change this template use File | Settings | File Templates.
 */
public class ErrorAction extends ActionSupport implements ModelDriven<ErrorActionModel> {

    @Override
    public String execute() throws Exception {
        return SUCCESS;
    }

    private ErrorActionModel model;

    public void setModel(ErrorActionModel model) {
        this.model = model;
    }

    @Override
    public ErrorActionModel getModel() {
        return this.model;
    }
}
