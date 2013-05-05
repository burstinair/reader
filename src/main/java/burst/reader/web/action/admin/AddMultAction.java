package burst.reader.web.action.admin;

import burst.reader.web.action.BaseAction;
import burst.reader.web.action.admin.model.AddMultActionModel;
import burst.reader.web.action.admin.service.AddMultService;
import com.opensymphony.xwork2.ModelDriven;

/**
 * Created with IntelliJ IDEA.
 * User: Burst
 * Date: 13-5-5
 * Time: 下午2:21
 * To change this template use File | Settings | File Templates.
 */
public class AddMultAction extends BaseAction implements ModelDriven<AddMultActionModel> {

    public void setAddMultService(AddMultService addMultService) {
        this.addMultService = addMultService;
    }

    private AddMultService addMultService;

    @Override
    public String execute() throws Exception {
        return addMultService.doExecute(addMultActionModel);
    }

    public String submit() throws Exception {
        return addMultService.doSubmit(addMultActionModel);
    }

    public AddMultActionModel getModel() {
        return addMultActionModel;
    }

    public void setAddMultActionModel(AddMultActionModel addMultActionModel) {
        this.addMultActionModel = addMultActionModel;
    }

    private AddMultActionModel addMultActionModel;
}
