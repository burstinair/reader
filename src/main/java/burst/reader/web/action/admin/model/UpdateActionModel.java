package burst.reader.web.action.admin.model;

import burst.reader.dto.BookDTO;
import burst.reader.web.action.model.BaseModel;

public class UpdateActionModel extends BaseModel {

    private Integer bookId;

    private String contentToAppend;

    private String version;

    private String action;

    private String status;

    private String message;

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public String getContentToAppend() {
        return contentToAppend;
    }

    public void setContentToAppend(String contentToAppend) {
        this.contentToAppend = contentToAppend;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}