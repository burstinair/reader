/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package burst.reader.web.action.admin;

import java.sql.SQLException;

import burst.reader.web.action.admin.service.EditService;
import burst.reader.BookException;
import burst.reader.web.action.BaseAction;
import burst.reader.web.action.admin.model.EditActionModel;

import com.opensymphony.xwork2.ModelDriven;


/**
 *
 * @author Burst
 */
public class EditAction extends BaseAction implements ModelDriven<EditActionModel> {
	
	private static final long serialVersionUID = -5513031436904238930L;

    public void setEditService(EditService editService) {
        this.editService = editService;
    }

    private EditService editService;

	public String execute() throws Exception {
        return editService.doExecute(editActionModel);
    }

    public String submit() throws BookException, SQLException
    {
        return editService.doSubmit(editActionModel);
    }

    private EditActionModel editActionModel;

    @Override
	public EditActionModel getModel() {
		return editActionModel;
	}

	public void setEditActionModel(EditActionModel editActionModel) {
		this.editActionModel = editActionModel;
	}
}
