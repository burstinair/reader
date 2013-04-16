/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package burst.reader.web.action.admin;

import burst.reader.web.action.BaseAction;
import burst.reader.web.action.admin.model.UpdateActionModel;
import com.opensymphony.xwork2.ModelDriven;

/**
 *
 * @author Burst
 */
public class UpdateAction extends BaseAction implements ModelDriven<UpdateActionModel> {

	private static final long serialVersionUID = 9049433841834398357L;

	public String execute() throws Exception {

        return SUCCESS;
    }

    private UpdateActionModel updateActionModel;

	@Override
	public UpdateActionModel getModel() {
		return updateActionModel;
	}
    
	public void setAdminActionModel(UpdateActionModel updateActionModel) {
		this.updateActionModel = updateActionModel;
	}
}
