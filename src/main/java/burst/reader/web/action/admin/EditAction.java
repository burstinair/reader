/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package burst.reader.web.action.admin;

import java.nio.charset.Charset;
import java.sql.SQLException;
import java.util.Date;

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
            editActionModel.setBook(bookService.loadBook(editActionModel.getUnboxedId()));
        } catch (Exception ex) {
            editActionModel.setBook(null);
            editActionModel.setId(0);
        }
        return SUCCESS;
    }

    public String submit() throws BookException, SQLException
    {
        boolean isAdd = editActionModel.getId() == null || editActionModel.getUnboxedId() == 0;
        boolean isSubmit = true;
        BookDTO book = null;
        editActionModel.setBook(null);
        try {
            book = new BookDTO();
            book.setId(editActionModel.getId());
            book.setName(editActionModel.getName());
            book.setAuthor(editActionModel.getAuthor());
            book.setVisible("visible");
            book.setContent(WebUtil.readAllText(editActionModel.getUpload(), Charset.forName("GBK")));
            editActionModel.setBook(book);
        } catch (Exception ex) {
            isSubmit = false;
        }
        if (isSubmit) {
            book.setAddDate(new Date());
            if (isAdd) {
                bookService.addBook(book);
            } else {
                bookService.update(book);
            }
        } else {
            if (!isAdd) {
                editActionModel.setBook(bookService.loadBook(editActionModel.getUnboxedId()));
            }
        }
        return "redirect";
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
