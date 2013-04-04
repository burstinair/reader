package burst.reader.web.action.admin.model;

import burst.reader.web.action.model.BookListModel;

public class AdminActionModel extends BookListModel {

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