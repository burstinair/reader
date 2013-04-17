/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package burst.reader.web.action.admin;

import java.nio.charset.Charset;
import java.sql.SQLException;
import java.util.Date;

import burst.web.util.WebUtil;
import burst.reader.BookException;
import burst.reader.dto.BookDTO;
import burst.reader.dto.BookUpdateRecordDTO;
import burst.reader.web.action.BaseAction;
import burst.reader.web.action.admin.model.EditActionModel;

import com.opensymphony.xwork2.ModelDriven;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;


/**
 *
 * @author Burst
 */
public class EditAction extends BaseAction implements ModelDriven<EditActionModel> {
	
	private static final long serialVersionUID = -5513031436904238930L;
	
	public String execute() throws Exception {
        try {
            editActionModel.setBook(bookService.loadBook(editActionModel.getUnboxedId()));
            loadVersionInModel();
        } catch (Exception ex) {
            editActionModel.setBook(null);
            editActionModel.setId(0);
        }
        return SUCCESS;
    }

    private void loadVersionInModel() {
        try {
            BookUpdateRecordDTO record = bookService.loadLastUpdateRecord(editActionModel.getUnboxedId());
            if(record == null) {
                editActionModel.setVersion("");
            } else {
                editActionModel.setVersion(record.getVersion());
            }
        } catch (SQLException ex) {
            editActionModel.setVersion("");
        }
    }

    public String submit() throws BookException, SQLException
    {
        HttpServletRequest request = ServletActionContext.getRequest();

        boolean isAdd = editActionModel.getId() == null || editActionModel.getUnboxedId() == 0;
        boolean isSubmit = true;
        boolean withoutContent = false;
        BookDTO book = null;
        editActionModel.setBook(null);
        try {
            book = new BookDTO();
            book.setId(editActionModel.getId());
            book.setName(editActionModel.getName());
            book.setAuthor(editActionModel.getAuthor());
            book.setVisible("visible");
            if(editActionModel.getUpload() == null) {
                withoutContent = true;
                if(isAdd) {
                    throw new Exception();
                }
            } else {
                book.setContent(WebUtil.readAllText(editActionModel.getUpload(), Charset.forName("GBK")));
            }
            editActionModel.setBook(book);
        } catch (Exception ex) {
            isSubmit = false;
        }
        if (isSubmit) {
            book.setAddDate(new Date());
            if (isAdd) {
                bookService.addBookAndAddRecord(book, WebUtil.getRemoteModel(), editActionModel.getVersion());
            } else {
                if(withoutContent) {
                    bookService.updateWithoutContentAndAddRecord(book, WebUtil.getRemoteModel(), editActionModel.getVersion());
                } else {
                    bookService.updateAndAddRecord(book, WebUtil.getRemoteModel(), editActionModel.getVersion());
                }
            }
        } else {
            if (!isAdd) {
                editActionModel.setBook(bookService.loadBook(editActionModel.getUnboxedId()));
            }
            loadVersionInModel();
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
