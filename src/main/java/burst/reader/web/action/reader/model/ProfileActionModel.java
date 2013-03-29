package burst.reader.web.action.reader.model;

import java.util.ArrayList;

import burst.reader.dto.BookMarkDTO;

public class ProfileActionModel {

    private Integer id;
    public void setId(Integer value)
    {
        id = value;
        unboxedId = value;
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
    
    private ArrayList<BookMarkDTO> bookmarks;
    public ArrayList<BookMarkDTO> getBookMarks()
    {
        return bookmarks;
    }
    
    public ArrayList<BookMarkDTO> getBookmarks() {
		return bookmarks;
	}
	public void setBookmarks(ArrayList<BookMarkDTO> bookmarks) {
		this.bookmarks = bookmarks;
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