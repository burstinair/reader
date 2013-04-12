package burst.reader.web.action.reader;

import burst.reader.dto.BookMarkDTO;
import burst.reader.web.action.BaseAction;
import burst.reader.web.action.reader.model.AuthorActionModel;

import com.opensymphony.xwork2.ModelDriven;

/**
 * Created with IntelliJ IDEA.
 * User: Burst
 * Date: 13-4-5
 * Time: 上午12:50
 * To change this template use File | Settings | File Templates.
 */
public class AuthorAction extends BaseAction implements ModelDriven<AuthorActionModel> {

	private static final long serialVersionUID = 8050607779504290668L;

	@Override
    public String execute() throws Exception {

        BookMarkDTO recent = bookMarkService.loadRecentByAuthor(model.getAuthor());

        if(recent != null) {
            model.setRecentBookMark(recent);
            model.setRecentBookName(bookService.loadName(recent.getBookId()));
        }

        if(model.getCurrentPage() == null) {
            model.setCurrentPage(1);
        }

        model.setBooks(bookService.loadVisibleByAuthorIndex(model, model.getAuthor()));

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
