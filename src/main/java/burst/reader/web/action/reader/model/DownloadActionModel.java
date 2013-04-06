package burst.reader.web.action.reader.model;

import java.io.InputStream;

/**
 * Created with IntelliJ IDEA.
 * User: Burst
 * Date: 13-4-6
 * Time: 下午7:28
 * To change this template use File | Settings | File Templates.
 */
public class DownloadActionModel {

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    private Integer id;

    public InputStream getStream() {
        return stream;
    }

    public void setStream(InputStream stream) {
        this.stream = stream;
    }

    private InputStream stream;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    private String fileName;

}
