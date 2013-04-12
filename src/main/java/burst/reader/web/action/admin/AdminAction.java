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
		
		if(adminActionModel.getCurrentPage() == null) {
			adminActionModel.setCurrentPage(1);
		}

        if(adminActionModel.getBookId() != null) {
            if("unvisible".equals(adminActionModel.getAction())) {
                bookService.updateVisible(adminActionModel.getBookId(), "unvisible");
            } else if("visible".equals(adminActionModel.getAction())) {
                bookService.updateVisible(adminActionModel.getBookId(), "visible");
            }
        }

        adminActionModel.setBooks(bookService.loadIndex(adminActionModel));

        return SUCCESS;
    }

    private AdminActionModel adminActionModel;

	@Override
	public AdminActionModel getModel() {
		return adminActionModel;
	}
    
	public void setAdminActionModel(AdminActionModel adminActionModel) {
		this.adminActionModel = adminActionModel;
	}
}
