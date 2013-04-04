package burst.reader.web.action.reader.model;

import burst.reader.dto.BookMarkDTO;
import burst.reader.web.action.model.PageModelImpl;
import java.util.Map;
import java.util.List;
import burst.reader.dto.BookDTO;

public class IndexActionModel extends PageModelImpl {

    private Map<String, List<BookDTO>> books;

    public Map<String, List<BookDTO>> getBooks() {
        return books;
    }

    public void setBooks(Map<String, List<BookDTO>> books) {
        this.books = books;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    private String author;

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    private String action;

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