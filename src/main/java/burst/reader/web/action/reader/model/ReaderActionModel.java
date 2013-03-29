package burst.reader.web.action.reader.model;

import burst.reader.web.action.model.PageModelImpl;

public class ReaderActionModel extends PageModelImpl {

	private String bookmarkAction;

	private boolean notExist;

    private Integer id;
    
    private int unboxedId;
    
    private boolean redirect;
    
    private String title;

	private String content;

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
		this.unboxedId = id;
	}

    public int getUnboxedId() {
		return unboxedId;
	}

	public void setUnboxedId(int unboxedId) {
		this.unboxedId = unboxedId;
		this.id = unboxedId;
	}

	public String getContent()
    {
    	StringBuilder res = new StringBuilder();
    	for (int i = 0, l = content.length(); i < l; ++i) {
    		char c = content.charAt(i);
    		if (c == '\r') {
    			if(i < l - 1) {
	    			if (content.charAt(i + 1) == '\n') {
	    				++i;
	    			}
    			}
    			res.append("<br />");
    		} else if (c == '\n') {
    			res.append("<br />");
    		} else if (c == ' ') {
    			res.append("&nbsp;");
    		} else if (c == '<') {
    			res.append("&lt;");
    		} else if (c == '>') {
    			res.append("&gt;");
    		} else if (c == '&') {
    			res.append("&amp;");
    		} else {
    			res.append(c);
    		}
    	}
        return res.toString();
        //return content;
    }

	public void setRedirect(boolean redirect) {
		this.redirect = redirect;
	}

	public boolean isRedirect() {
		return redirect;
	}
}
