package burst.reader.web.action.service;

import burst.reader.service.BookMarkService;
import burst.reader.service.BookService;

/**
 * Created with IntelliJ IDEA.
 * User: Burst
 * Date: 13-4-17
 * Time: 下午4:56
 * To change this template use File | Settings | File Templates.
 */
public abstract class BaseService {
    protected BookService bookService;

    public void setBookMarkService(BookMarkService bookMarkService) {
        this.bookMarkService = bookMarkService;
    }

    public void setBookService(BookService bookService) {
        this.bookService = bookService;
    }

    protected BookMarkService bookMarkService;
}
