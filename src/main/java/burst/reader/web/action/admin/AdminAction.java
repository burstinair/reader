/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package burst.reader.web.action.admin;

import burst.reader.web.action.BaseAction;
import burst.reader.web.action.admin.model.AdminActionModel;

import com.opensymphony.xwork2.ModelDriven;

/**
 *
 * @author Burst
 */
public class AdminAction extends BaseAction implements ModelDriven<AdminActionModel> {

	private static final long serialVersionUID = 9049433841834398307L;

	public String execute() throws Exception {
		
		if(model.getCurrentPage() == null) {
			model.setCurrentPage(1);
		}
		
        model.setBooks(BookDAO.getIndex(model));
        return SUCCESS;
    }

    private AdminActionModel model;

	@Override
	public AdminActionModel getModel() {
		return model;
	}
    
	public void setModel(AdminActionModel model) {
		this.model = model;
	}
}
