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
        
        	if(readerActionModel.isRedirect() != null && readerActionModel.isRedirect()) {
        		return "redirect";
        	}
        	
        	if(readerActionModel.getId() == null) {
        		throw new BookException();
        	}
        	
        	readerActionModel.setNotExist(false);
        	
            readerActionModel.setTitle(bookService.loadName(readerActionModel.getUnboxedId()));
        	readerActionModel.setContent(getFiltedContent(
                bookService.loadPagedContent(readerActionModel.getUnboxedId(), readerActionModel),
                readerActionModel.getContentFilter()
            ));

            dealBookMark();
        
        } catch (BookException ex) {
        	readerActionModel.setNotExist(true);
        }
        
        return SUCCESS;
    }

    private void dealBookMark() {

        HttpServletRequest request = ServletActionContext.getRequest();

        BookMarkDTO bookmark = new BookMarkDTO();
        bookmark.setBookId(readerActionModel.getId());
        bookmark.setAddDate(new Date());
        bookmark.setPage(readerActionModel.getCurrentPage());
        bookmark.setWordCount(readerActionModel.getPageSize());
        bookmark.setIp(request.getRemoteAddr());
        bookmark.setUserAgent(request.getHeader("User-Agent"));

        if ("normal".equals(readerActionModel.getBookmarkAction())) {
            bookmark.setSpecial(BookMarkService.NORMAL);
        } else if("single".equals(readerActionModel.getBookmarkAction())) {
            bookmark.setSpecial(BookMarkService.SINGLE);
        }

        bookMarkMonitorService.push(bookmark);
    }

    private String getFiltedContent(String content, String contentFilter) {

        if(content == null) {
            return "";
        }

        if(!"".equals(contentFilter)) {
            content = content.replaceAll(contentFilter, "");
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
