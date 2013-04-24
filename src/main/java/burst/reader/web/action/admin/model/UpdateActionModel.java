package burst.reader.web.action.admin.model;

import burst.commons.model.ResultModel;
import burst.reader.web.action.model.BaseModel;

import java.util.HashMap;
import java.util.Map;

public class UpdateActionModel extends BaseModel implements ResultModel {

    private Integer bookId;

    private String contentToAppend;

    private String fromVersion;

    private String toVersion;

    private String version;

    private String action;

    private String status;

    private Map<String, Object> result = new HashMap<String, Object>();

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

    public String getToVersion() {
        return toVersion;
    }

    public void setToVersion(String toVersion) {
        this.toVersion = toVersion;
    }

    public String getFromVersion() {
        return fromVersion;
    }

    public void setFromVersion(String fromVersion) {
        this.fromVersion = fromVersion;
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
        return (String)result.get("status");
    }

    public void setStatus(String status) {
        result.put("status", status);
    }

    public String getMessage() {
        return (String)result.get("message");
    }

    public void setMessage(String message) {
        result.put("message", message);
    }

    public Object getResult() {
        return result;
    }

    public Map<String, Object> getResultMap() {
        return result;
    }
}