/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package admin;

import com.opensymphony.xwork2.ActionSupport;
import java.util.ArrayList;
import model.Book;
import org.apache.struts2.rest.DefaultHttpHeaders;
import org.apache.struts2.rest.HttpHeaders;
import service.BookService;

/**
 *
 * @author Burst
 */
public class AdminController extends ActionSupport {

    public HttpHeaders index() throws Exception {
        _books = BookService.getIndex();
        return new DefaultHttpHeaders(SUCCESS).disableCaching();
    }
    
    private ArrayList<Book> _books;
    public ArrayList<Book> getBooks()
    {
        return _books;
    }
}
