package burst.reader.web.action.reader;

import burst.reader.BookException;
import burst.reader.dto.BookDTO;
import burst.reader.dto.BookMarkDTO;
import burst.reader.web.action.BaseAction;
import burst.reader.web.action.reader.model.AuthorActionModel;
import com.opensymphony.xwork2.ModelDriven;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpServerErrorException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Burst
 * Date: 13-4-5
 * Time: 上午12:50
 * To change this template use File | Settings | File Templates.
 */
public class AuthorAction extends BaseAction implements ModelDriven<AuthorActionModel> {

    @Override
    public String execute() throws Exception {

        BookMarkDTO recent = bookMarkService.loadRecentByAuthor(model.getAuthor());

        if(recent != null) {
            model.setRecentBookMark(recent);
            model.setRecentBookName(bookService.getName(recent.getBookId()));
        }

        if(model.getCurrentPage() == null) {
            model.setCurrentPage(1);
        }

        model.setBooks(bookService.getVisibleByAuthorIndex(model, model.getAuthor()));

        return SUCCESS;
    }

    public void setModel(AuthorActionModel model) {
        this.model = model;
    }

    private AuthorActionModel model;

    @Override
    public AuthorActionModel getModel() {
        return this.model;
    }
}
