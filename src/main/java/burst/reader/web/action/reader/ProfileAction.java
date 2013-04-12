/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package burst.reader.web.action.reader;

import burst.reader.BookException;
import burst.reader.web.action.BaseAction;
import burst.reader.web.action.reader.model.ProfileActionModel;

import com.opensymphony.xwork2.ModelDriven;

/**
 *
 * @author Burst
 */
public class ProfileAction extends BaseAction implements ModelDriven<ProfileActionModel> {
	
	private static final long serialVersionUID = 3831059014880366458L;
	
	public String execute() throws Exception {
        try {
            if(profileActionModel.getId() == null)
                throw new BookException();
            profileActionModel.setBookmarks(bookMarkService.loadBookMarks(profileActionModel.getUnboxedId()));
            profileActionModel.setBookName(bookService.loadName(profileActionModel.getUnboxedId()));
            profileActionModel.setNotExist(false);
        } catch (BookException ex) {
            profileActionModel.setNotExist(true);
        }
        return SUCCESS;
    }

	private ProfileActionModel profileActionModel;

	@Override
	public ProfileActionModel getModel() {
		return profileActionModel;
	}

	public void setProfileActionModel(ProfileActionModel profileActionModel) {
		this.profileActionModel = profileActionModel;
	}
}
