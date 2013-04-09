package burst.reader.web.action.reader.model;

import java.util.List;

import burst.reader.dto.BookMarkDTO;
import burst.reader.web.action.model.BaseModel;

public class ProfileActionModel extends BaseModel {

    private Integer id;
    public void setId(Integer value)
    {
        id = value;
        if(value == null) {
        	this.unboxedId = 0;
        } else {
	        unboxedId = value;
	    }
    }
    public Integer getId()
    {
        return id;
    }
    
    private int unboxedId;
    public int getUnboxedId() {
    	return unboxedId;
    }

    private String bookName;
    public String getBookName()
    {
        return bookName;
    }
    
    private List<BookMarkDTO> bookmarks;
    public List<BookMarkDTO> getBookMarks()
    {
        return bookmarks;
    }
    
    public List<BookMarkDTO> getBookmarks() {
		return bookmarks;
	}
	public void setBookmarks(List<BookMarkDTO> list) {
		this.bookmarks = list;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public void setNotExist(boolean notExist) {
		this.notExist = notExist;
	}

	private boolean notExist;
    public boolean getNotExist()
    {
        return notExist;
    }
}