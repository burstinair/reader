/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package reader;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.util.Date;
import java.util.Map;
import service.BookException;
import model.BookMark;
import service.BookMarkService;
import service.BookService;

/**
 *
 * @author Burst
 */
public class Reader extends ActionSupport {
    
    @Override
    public String execute() throws Exception {
        
        ActionContext context = ActionContext.getContext();
        Map<String, Object> params = context.getParameters();
        
        try {
        
            _not_exist = false;
            
            int id;
            try { 
                id = Integer.parseInt(((String[])params.get("id"))[0]);
            } catch (Exception ex) {
                throw new BookException();
            }
            try {
                cur_page = Integer.parseInt(((String[])params.get("cp"))[0]);
            } catch(Exception ex) {
                cur_page = 1;
            }
            try {
                word_count = Integer.parseInt(((String[])params.get("wc"))[0]);
            } catch(Exception ex) {
                word_count = Integer.parseInt(this.getText("word_count"));
            }

            page_count = BookService.getPageCount(id, word_count);
            try {
                _isfirstpage = cur_page == 1;
                _islastpage = cur_page == page_count;
                _title = BookService.getName(id);
                _content = BookService.getContent(id, cur_page, word_count);
            } catch(BookException ex) {
                _isfirstpage = _islastpage = true;
                _title = _content = "This book does not exist.";
            }

            BookMark bookmark = new BookMark();
            bookmark.setBookId(id);
            bookmark.setAddDate(new Date());
            bookmark.setPage(cur_page);
            bookmark.setWordCount(word_count);
            bookmark.setIsAutoSave("true");

            BookMarkService.addAutoSaveBookMark(bookmark);
            String[] actions = (String[])params.get("action");
            if (actions != null && actions.length > 0) {
	            if (actions[0].equals("bookmark")) {
                    bookmark.setIsAutoSave("ufalse");
	                BookMarkService.addBookMark(bookmark);
	            }
            }
        
        } catch (BookException ex) {
            _not_exist = true;
            _title = "This book does not exist.";
        }
        
        return SUCCESS;
    }
    
    private boolean _not_exist;
    public boolean getNotExist()
    {
        return _not_exist;
    }
    
    private int word_count;
    public int getWordCount()
    {
        return word_count;
    }
    
    private int cur_page;
    public int getCurPage()
    {
        return cur_page;
    }
    
    private int page_count;
    public int getPageCount()
    {
        return page_count;
    }
    
    private boolean _isfirstpage;
    public boolean getIsFirstPage()
    {
        return _isfirstpage;
    }
    
    private boolean _islastpage;
    public boolean getIsLastPage()
    {
        return _islastpage;
    }
    
    private String _title;
    public String getTitle()
    {
        return _title;
    }
    
    private String _content;
    public String getContent()
    {
    	StringBuilder res = new StringBuilder();
    	for (int i = 0, l = _content.length(); i < l; ++i) {
    		char c = _content.charAt(i);
    		if (c == '\r') {
    			if(i < l - 1) {
	    			if (_content.charAt(i + 1) == '\n') {
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
        //return _content;
    }
}
