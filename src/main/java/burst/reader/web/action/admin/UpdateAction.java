/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package burst.reader.web.action.admin;

import burst.reader.web.action.BaseAction;
import burst.reader.web.action.admin.model.UpdateActionModel;
import burst.reader.web.action.admin.service.UpdateService;
import com.opensymphony.xwork2.ModelDriven;

/**
 *
 * @author Burst
 */
public class UpdateAction extends BaseAction implements ModelDriven<UpdateActionModel> {

	private static final long serialVersionUID = 9049433841834398357L;

    public void setUpdateService(UpdateService updateService) {
        this.updateService = updateService;
    }

    private UpdateService updateService;

	public String execute() throws Exception {
        return updateService.doExecute(updateActionModel);
    }

    private UpdateActionModel updateActionModel;

	@Override
	public UpdateActionModel getModel() {
		return updateActionModel;
	}
    
	public void setUpdateActionModel(UpdateActionModel updateActionModel) {
		this.updateActionModel = updateActionModel;
	}
}
