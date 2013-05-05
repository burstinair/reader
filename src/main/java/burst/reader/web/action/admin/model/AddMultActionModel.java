package burst.reader.web.action.admin.model;

import burst.reader.web.action.model.BaseModel;

import java.io.File;

/**
 * Created with IntelliJ IDEA.
 * User: Burst
 * Date: 13-5-5
 * Time: 下午2:21
 * To change this template use File | Settings | File Templates.
 */
public class AddMultActionModel extends BaseModel {

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    private String version;

    private File upload;
    public File getUpload()
    {
        return upload;
    }
    public void setUpload(File value)
    {
        upload = value;
    }

    private String uploadFileName;
    public String getUploadFileName()
    {
        return uploadFileName;
    }
    public void setUploadFileName(String value)
    {
        uploadFileName = value;
    }

    private String uploadContentType;
    public String getUploadContentType()
    {
        return uploadContentType;
    }
    public void setUploadContentType(String value)
    {
        uploadContentType = value;
    }

    public Boolean isSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    private Boolean success;
}
