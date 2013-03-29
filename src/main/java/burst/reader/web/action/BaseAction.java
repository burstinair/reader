package burst.reader.web.action;

import burst.reader.dao.BookDAO;
import burst.reader.dao.BookMarkDAO;

import com.opensymphony.xwork2.ActionSupport;

public abstract class BaseAction extends ActionSupport {

	private static final long serialVersionUID = -6247972957717364079L;
	
	protected BookDAO BookDAO;
	
	public void setBookDAO(BookDAO bookDAO) {
		BookDAO = bookDAO;
	}
	
	protected BookMarkDAO BookMarkDAO;

	public void setBookMarkDAO(BookMarkDAO bookMarkDAO) {
		BookMarkDAO = bookMarkDAO;
	}
	
}
