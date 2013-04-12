/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package burst.reader.web.action.reader;

import java.util.Date;

import com.opensymphony.xwork2.ModelDriven;

import burst.reader.BookException;
import burst.reader.dto.BookMarkDTO;
import burst.reader.service.BookMarkMonitorService;
import burst.reader.service.BookMarkService;
import burst.reader.web.action.BaseAction;
import burst.reader.web.action.reader.model.ReaderActionModel;

/**
 *
 * @author Burst
 */
public class ReaderAction extends BaseAction implements ModelDriven<ReaderActionModel> {
	
	private static final long serialVersionUID = -2511632648336749729L;
	
	public String execute() throws Exception {
        try {
        
        	if(model.isRedirect() != null && model.isRedirect()) {
        		return "redirect";
        	}
        	
        	if(model.getId() == null) {
        		throw new BookException();
        	}
        	
        	model.setNotExist(false);
        	
            model.setTitle(bookService.loadName(model.getUnboxedId()));
        	model.setContent(bookService.loadPagedContent(model.getUnboxedId(), model));

            BookMarkDTO bookmark = new BookMarkDTO();
            bookmark.setBookId(model.getId());
            bookmark.setAddDate(new Date());
            bookmark.setPage(model.getCurrentPage());
            bookmark.setWordCount(model.getPageSize());

            if ("normal".equals(model.getBookmarkAction())) {
                bookmark.setSpecial(BookMarkService.NORMAL);
            } else if("single".equals(model.getBookmarkAction())) {
                bookmark.setSpecial(BookMarkService.SINGLE);
            }
            
            bookMarkMonitorService.push(bookmark);
        
        } catch (BookException ex) {
        	model.setNotExist(true);
        }
        
        return SUCCESS;
    }
	
	private BookMarkMonitorService bookMarkMonitorService;

	public void setBookMarkMonitorService(BookMarkMonitorService bookMarkMonitorService) {
		this.bookMarkMonitorService = bookMarkMonitorService;
	}

	public BookMarkMonitorService getBookMarkMonitorService() {
		return bookMarkMonitorService;
	}
	
	private ReaderActionModel model;

	@Override
	public ReaderActionModel getModel() {
		return model;
	}

	public void setModel(ReaderActionModel model) {
		this.model = model;
	}
}
