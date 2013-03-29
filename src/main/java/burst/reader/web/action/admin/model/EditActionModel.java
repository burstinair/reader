package burst.reader.web.action.admin.model;

import java.io.File;

import burst.reader.dto.BookDTO;

public class EditActionModel {

    private Integer id;
    public Integer getId()
    {
        return id;
    }
    public void setId(Integer value)
    {
        id = value;
        unboxedId = value;
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
}