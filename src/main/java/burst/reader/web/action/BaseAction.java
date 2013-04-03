package burst.reader.web.action;

import burst.reader.service.BookService;
import burst.reader.service.BookMarkService;

import com.opensymphony.xwork2.ActionSupport;

public abstract class BaseAction extends ActionSupport {

	private static final long serialVersionUID = -6247972957717364079L;
	
	protected BookService bookService;
	
	public void setBookDAO(BookService bookService) {
		this.bookService = bookService;
	}
	
	protected BookMarkService bookMarkService;

	public void setBookMarkDAO(BookMarkService bookMarkService) {
		this.bookMarkService = bookMarkService;
	}
	
}
