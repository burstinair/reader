/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package admin;

import com.opensymphony.xwork2.ActionSupport;
import java.util.ArrayList;
import model.Book;
import service.BookService;

/**
 *
 * @author Burst
 */
public class Admin extends ActionSupport {
    
    @Override
    public String execute() throws Exception {
        _books = BookService.getIndex();
        return SUCCESS;
    }
    
    private ArrayList<Book> _books;
    public ArrayList<Book> getBooks()
    {
        return _books;
    }
}
