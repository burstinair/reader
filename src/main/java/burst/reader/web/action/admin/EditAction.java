/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package burst.reader.web.action.admin;

import java.nio.charset.Charset;

import burst.reader.BookException;
import burst.reader.dto.BookDTO;
import burst.reader.util.WebUtil;
import burst.reader.web.action.BaseAction;
import burst.reader.web.action.admin.model.EditActionModel;

import com.opensymphony.xwork2.ModelDriven;


/**
 *
 * @author Burst
 */
public class EditAction extends BaseAction implements ModelDriven<EditActionModel> {
	
	private static final long serialVersionUID = -5513031436904238930L;
	
	public String execute() throws Exception {
        try {
            model.setBook(BookDAO.getBook(model.getUnboxedId()));
        } catch (Exception ex) {
            model.setBook(null);
            model.setId(0);
        }
        return SUCCESS;
    }

    public String submit() throws BookException
    {
        boolean isAdd = model.getId() == null || model.getUnboxedId() == 0;
        boolean isSubmit = true;
        BookDTO book = null;
        model.setBook(null);
        try {
            book = new BookDTO();
            book.setId(model.getId());
            book.setName(model.getName());
            book.setContent(WebUtil.readAllText(model.getUpload(), Charset.forName("GBK")));
            model.setBook(book);
        } catch (Exception ex) {
            isSubmit = false;
        }
        if (isSubmit) {
            if (isAdd) {
                BookDAO.addBook(book);
            } else {
                BookDAO.update(book);
            }
        } else {
            if (!isAdd) {
                model.setBook(BookDAO.getBook(model.getUnboxedId()));
            }
        }
        return "redirect";
    }

    private EditActionModel model;

    @Override
	public EditActionModel getModel() {
		return model;
	}

	public void setModel(EditActionModel model) {
		this.model = model;
	}
}
