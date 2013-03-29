/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package burst.reader.web.action.admin;

import com.opensymphony.xwork2.ModelDriven;

import burst.reader.web.action.BaseAction;
import burst.reader.web.action.admin.model.ExecuteSQLActionModel;

/**
 *
 * @author Burst
 */
public class ExecuteSQLAction extends BaseAction implements ModelDriven<ExecuteSQLActionModel> {

	private static final long serialVersionUID = 8881556805633489846L;
	
	public String execute() {
        
        return SUCCESS;
    }

    private ExecuteSQLActionModel model;

    @Override
	public ExecuteSQLActionModel getModel() {
		return model;
	}
    
	public void setModel(ExecuteSQLActionModel model) {
		this.model = model;
	}
}
