/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package reader;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.util.ArrayList;
import java.util.Map;
import service.BookException;
import model.BookMark;
import service.BookMarkService;

/**
 *
 * @author Burst
 */
public class Profile extends ActionSupport {
    
    @Override
    public String execute() throws Exception {
        ActionContext context = ActionContext.getContext();
        Map<String, Object> params = context.getParameters();
        
        try {
            int id;
            try {
                id = Integer.parseInt(((String[])params.get("id"))[0]);
            } catch (Exception ex) {
                throw new BookException();
            }
            _bookmarks = BookMarkService.getBookMarks(id);
            _not_exist = false;
        } catch (BookException ex) {
            _not_exist = true;
        }
        
        return SUCCESS;
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
