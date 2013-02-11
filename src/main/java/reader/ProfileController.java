/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package reader;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.util.ArrayList;
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
public class ProfileController extends ActionSupport {

    public HttpHeaders index() throws Exception {
        _not_exist = true;
        return new DefaultHttpHeaders(SUCCESS).disableCaching();
    }

    public String show() throws Exception {
        try {
            if(_id == null)
                throw new BookException();
            _bookmarks = BookMarkService.getBookMarks(_id);
            _book_name = BookService.getName(_id);
            _not_exist = false;
        } catch (BookException ex) {
            _not_exist = true;
        }
        return SUCCESS;
    }

    private Integer _id;
    public void setId(Integer value)
    {
        _id= value;
    }
    public Integer getId()
    {
        return _id;
    }

    private String _book_name;
    public String getBookName()
    {
        return _book_name;
    }
    
    private ArrayList<BookMark> _bookmarks;
    public ArrayList<BookMark> getBookMarks()
    {
        return _bookmarks;
    }
    
    private boolean _not_exist;
    public boolean getNotExist()
    {
        return _not_exist;
    }
}
