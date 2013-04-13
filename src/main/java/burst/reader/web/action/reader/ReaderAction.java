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
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Burst
 */
public class ReaderAction extends BaseAction implements ModelDriven<ReaderActionModel> {
	
	private static final long serialVersionUID = -2511632648336749729L;
	
	public String execute() throws Exception {
        try {

            HttpServletRequest request = ServletActionContext.getRequest();
        
        	if(readerActionModel.isRedirect() != null && readerActionModel.isRedirect()) {
        		return "redirect";
        	}
        	
        	if(readerActionModel.getId() == null) {
        		throw new BookException();
        	}
        	
        	readerActionModel.setNotExist(false);
        	
            readerActionModel.setTitle(bookService.loadName(readerActionModel.getUnboxedId()));
        	readerActionModel.setContent(bookService.loadPagedContent(readerActionModel.getUnboxedId(), readerActionModel));

            String userAgent = request.getHeader("User-Agent");
            if(!"".equals(readerActionModel.getUserAgentFilter())) {
                if(userAgent.matches(readerActionModel.getUserAgentFilter())) {
                    return SUCCESS;
                }
            }

            BookMarkDTO bookmark = new BookMarkDTO();
            bookmark.setBookId(readerActionModel.getId());
            bookmark.setAddDate(new Date());
            bookmark.setPage(readerActionModel.getCurrentPage());
            bookmark.setWordCount(readerActionModel.getPageSize());
            bookmark.setIp(request.getRemoteAddr());
            bookmark.setUserAgent(userAgent);

            if ("normal".equals(readerActionModel.getBookmarkAction())) {
                bookmark.setSpecial(BookMarkService.NORMAL);
            } else if("single".equals(readerActionModel.getBookmarkAction())) {
                bookmark.setSpecial(BookMarkService.SINGLE);
            }
            
            bookMarkMonitorService.push(bookmark);
        
        } catch (BookException ex) {
        	readerActionModel.setNotExist(true);
        }
        
        return SUCCESS;
    }
	
	private BookMarkMonitorService bookMarkMonitorService;

	public void setBookMarkMonitorService(BookMarkMonitorService bookMarkMonitorService) {
		this.bookMarkMonitorService = bookMarkMonitorService;
	}
	
	private ReaderActionModel readerActionModel;

	@Override
	public ReaderActionModel getModel() {
		return readerActionModel;
	}

	public void setReaderActionModel(ReaderActionModel readerActionModel) {
		this.readerActionModel = readerActionModel;
	}
}
