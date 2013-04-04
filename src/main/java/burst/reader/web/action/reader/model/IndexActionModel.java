package burst.reader.web.action.reader.model;

import burst.reader.dto.BookMarkDTO;
import burst.reader.web.action.model.BookListModel;

public class IndexActionModel extends BookListModel {

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