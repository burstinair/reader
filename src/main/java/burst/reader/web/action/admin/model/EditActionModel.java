package burst.reader.web.action.admin.model;

import java.io.File;

import burst.reader.dto.BookDTO;
import burst.reader.web.action.model.BaseModel;

public class EditActionModel extends BaseModel {

    private Integer id;
    public Integer getId()
    {
        return id;
    }
    public void setId(Integer value)
    {
        id = value;
        if(value == null) {
        	unboxedId = 0;
        } else {
        	unboxedId = value;
        }
    }
    
    private int unboxedId;
    public int getUnboxedId() {
    	return unboxedId;
    }

    private String name;
    public String getName()
    {
        return name;
    }
    public void setName(String value)
    {
        name = value;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    private String author;

    public void setBook(BookDTO book) {
		this.book = book;
	}

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

    private BookDTO book;
    public BookDTO getBook()
    {
        return book;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    private String version;

    private Boolean isAdd;

    public Boolean isSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public Boolean isAdd() {
        return isAdd;
    }

    public void setAdd(Boolean add) {
        isAdd = add;
    }

    private Boolean success;
}