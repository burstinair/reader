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

        BookMarkDTO recent = bookMarkService.loadRecentByAuthor(authorActionModel.getAuthor());

        if(recent != null) {
            authorActionModel.setRecentBookMark(recent);
            authorActionModel.setRecentBookName(bookService.loadName(recent.getBookId()));
        }

        if(authorActionModel.getCurrentPage() == null) {
            authorActionModel.setCurrentPage(1);
        }

        authorActionModel.setBooks(bookService.loadVisibleByAuthorIndex(authorActionModel, authorActionModel.getAuthor()));

        return SUCCESS;
    }

    public void setAuthorActionModel(AuthorActionModel authorActionModel) {
        this.authorActionModel = authorActionModel;
    }

    private AuthorActionModel authorActionModel;

    @Override
    public AuthorActionModel getModel() {
        return this.authorActionModel;
    }
}
