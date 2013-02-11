/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package reader;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.util.Date;
import java.util.Map;

import org.apache.struts2.rest.DefaultHttpHeaders;
import org.apache.struts2.rest.HttpHeaders;
import service.BookException;
import model.BookMark;
import service.BookMarkService;
import service.BookService;

/**
 *
 * @author Burst
 */
public class ReaderController extends ActionSupport {

    public HttpHeaders index() throws Exception {
        _not_exist = true;
        return new DefaultHttpHeaders(SUCCESS).disableCaching();
    }

    public String show() throws Exception {
        
        try {
        
            _not_exist = false;

            int id = parseId();

            page_count = BookService.getPageCount(id, word_count);
            _isfirstpage = cur_page == 1;
            _islastpage = cur_page == page_count;
            _title = BookService.getName(id);
            _content = BookService.getContent(id, cur_page, word_count);

            BookMark bookmark = new BookMark();
            bookmark.setBookId(id);
            bookmark.setAddDate(new Date());
            bookmark.setPage(cur_page);
            bookmark.setWordCount(word_count);
            bookmark.setIsAutoSave("true");

            BookMarkService.addAutoSaveBookMark(bookmark);
            if (add_bookmark) {
                bookmark.setIsAutoSave("ufalse");
                BookMarkService.addBookMark(bookmark);
            }
        
        } catch (BookException ex) {
            _not_exist = true;
        }
        
        return SUCCESS;
    }

    private boolean add_bookmark;
    private Integer _parsed_id;
    public Integer getParsedId()
    {
        return _parsed_id;
    }

    private int parseId() throws BookException
    {
        if(_id == null) {
            throw new BookException();
        }

        String[] params = _id.split("_");

        try {
            _parsed_id = Integer.parseInt(params[0]);
        } catch (Exception ex) {
            throw new BookException();
        }

        try {
            cur_page = Integer.parseInt(params[1]);
        } catch (Exception ex) {
            cur_page = 1;
        }

        try {
            word_count = Integer.parseInt(params[2]);
        } catch (Exception ex) {
            word_count = Integer.parseInt(this.getText("word_count"));
        }

        try {
            add_bookmark = params[3].equals("abm");
        } catch (Exception ex) {
            add_bookmark = false;
        }

        return _parsed_id;
    }

    private String _id;
    public void setId(String value)
    {
        _id = value;
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
