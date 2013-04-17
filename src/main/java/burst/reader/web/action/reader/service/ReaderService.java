package burst.reader.web.action.reader.service;

import burst.reader.BookException;
import burst.reader.dto.BookMarkDTO;
import burst.reader.service.BookMarkMonitorService;
import burst.reader.service.BookMarkService;
import burst.reader.web.action.reader.model.ReaderActionModel;
import burst.reader.web.action.service.BaseService;
import burst.web.model.RemoteModel;
import burst.web.util.WebUtil;
import com.opensymphony.xwork2.ActionSupport;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Burst
 * Date: 13-4-17
 * Time: 下午5:07
 * To change this template use File | Settings | File Templates.
 */
public class ReaderService extends BaseService {

    public String doExecute(ReaderActionModel readerActionModel) throws Exception {
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

            dealBookMark(readerActionModel);

        } catch (BookException ex) {
            readerActionModel.setNotExist(true);
        }

        return ActionSupport.SUCCESS;
    }

    private void dealBookMark(ReaderActionModel readerActionModel) {

        RemoteModel remoteModel = WebUtil.getRemoteModel();

        BookMarkDTO bookmark = new BookMarkDTO();
        bookmark.setBookId(readerActionModel.getId());
        bookmark.setAddDate(new Date());
        bookmark.setPage(readerActionModel.getCurrentPage());
        bookmark.setWordCount(readerActionModel.getPageSize());
        bookmark.setIp(remoteModel.getRemoteAddr());
        bookmark.setUserAgent(remoteModel.getUserAgent());

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

        return WebUtil.EncodeCRLF(content);
    }

    private BookMarkMonitorService bookMarkMonitorService;

    public void setBookMarkMonitorService(BookMarkMonitorService bookMarkMonitorService) {
        this.bookMarkMonitorService = bookMarkMonitorService;
    }
}
