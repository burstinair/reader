package burst.reader.web.action.admin.service;

import burst.reader.BookException;
import burst.reader.dto.BookDTO;
import burst.reader.dto.BookUpdateRecordDTO;
import burst.reader.service.BookService;
import burst.reader.web.action.admin.model.EditActionModel;
import burst.reader.web.action.service.BaseService;
import burst.web.util.WebUtil;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.Charset;
import java.sql.SQLException;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Burst
 * Date: 13-4-17
 * Time: 下午4:50
 * To change this template use File | Settings | File Templates.
 */
public class EditService extends BaseService {

    public String doExecute(EditActionModel editActionModel) throws Exception {
        try {
            editActionModel.setBook(bookService.loadBook(editActionModel.getUnboxedId()));
            loadVersionInModel(editActionModel);
        } catch (Exception ex) {
            editActionModel.setBook(null);
            editActionModel.setId(0);
        }
        return ActionSupport.SUCCESS;
    }

    private void loadVersionInModel(EditActionModel editActionModel) {
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

    public String doSubmit(EditActionModel editActionModel) throws BookException, SQLException
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
            loadVersionInModel(editActionModel);
        }
        return "redirect";
    }
}
