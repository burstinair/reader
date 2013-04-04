package burst.reader.web.action.reader.model;

import burst.reader.dto.BookDTO;
import burst.reader.dto.BookMarkDTO;
import burst.reader.web.action.model.PageModelImpl;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Burst
 * Date: 13-4-5
 * Time: 上午12:50
 * To change this template use File | Settings | File Templates.
 */
public class AuthorActionModel extends PageModelImpl {

    public List<BookDTO> getBooks() {
        return books;
    }

    public void setBooks(List<BookDTO> books) {
        this.books = books;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    private List<BookDTO> books;

    private String author;

    private BookMarkDTO recentBookMark;

    private String recentBookName;

    public BookMarkDTO getRecentBookMark() {
        return recentBookMark;
    }

    public void setRecentBookMark(BookMarkDTO recentBookMark) {
        this.recentBookMark = recentBookMark;
    }

    public String getRecentBookName() {
        return recentBookName;
    }

    public void setRecentBookName(String recentBookName) {
        this.recentBookName = recentBookName;
    }
}
