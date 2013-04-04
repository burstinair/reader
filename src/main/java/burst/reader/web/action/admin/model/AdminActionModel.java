package burst.reader.web.action.admin.model;

import burst.reader.dto.BookDTO;
import burst.reader.web.action.model.PageModelImpl;

import java.util.List;

public class AdminActionModel extends PageModelImpl {

    private List<BookDTO> books;

    public void setBooks(List<BookDTO> books) {
        this.books = books;
    }
    public List<BookDTO> getBooks()
    {
        return books;
    }

    private String action;

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    private Integer bookId;
}