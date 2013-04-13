package burst.reader.web.action.reader.model;

import burst.reader.web.action.model.PageModelImpl;

public class ReaderActionModel extends PageModelImpl {

	private String bookmarkAction;

	private boolean notExist;

    private Integer id;
    
    private int unboxedId;
    
    private Boolean redirect;
    
    private String title;

	private String content;

    private String contentFilter;

    public String getBookmarkAction() {
		return bookmarkAction;
	}

	public void setBookmarkAction(String bookmarkAction) {
		this.bookmarkAction = bookmarkAction;
	}
    
    public boolean isNotExist() {
		return notExist;
	}

	public void setNotExist(boolean notExist) {
		this.notExist = notExist;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getContent() {
        return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getTitle()
    {
        return title;
    }

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
		if(id == null) {
			this.unboxedId = 0;
		} else {
			this.unboxedId = id;
		}
	}

    public int getUnboxedId() {
		return unboxedId;
	}

	public void setRedirect(Boolean redirect) {
		this.redirect = redirect;
	}

	public Boolean isRedirect() {
		return redirect;
	}

    public String getContentFilter() {
        return contentFilter;
    }

    public void setContentFilter(String contentFilter) {
        this.contentFilter = contentFilter;
    }

}
