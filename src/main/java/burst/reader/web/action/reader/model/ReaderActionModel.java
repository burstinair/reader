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

    public String getContentFilter() {
        return contentFilter;
    }

    public void setContentFilter(String contentFilter) {
        this.contentFilter = contentFilter;
    }

    private String contentFilter;

    public String getUserAgentFilter() {
        return userAgentFilter;
    }

    public void setUserAgentFilter(String userAgentFilter) {
        this.userAgentFilter = userAgentFilter;
    }

    private String userAgentFilter;

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
		if(content == null) {
			return "";
		}

        String content;
        if(!"".equals(contentFilter)) {
            content = this.content.replaceAll(contentFilter, "");
        } else {
            content = this.content;
        }
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
}
