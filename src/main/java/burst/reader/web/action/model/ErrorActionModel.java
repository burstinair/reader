package burst.reader.web.action.model;

/**
 * Created with IntelliJ IDEA.
 * User: Burst
 * Date: 13-4-5
 * Time: 上午12:50
 * To change this template use File | Settings | File Templates.
 */
public class ErrorActionModel extends BaseModel {

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    private String title;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    private String message;
}
