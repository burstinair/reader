package burst.reader.web.action.admin.service;

import burst.json.JsonUtils;
import burst.reader.BookException;
import burst.reader.dto.BookDTO;
import burst.reader.dto.BookUpdateRecordDTO;
import burst.reader.web.action.admin.model.UpdateActionModel;
import burst.reader.web.action.service.BaseService;
import burst.web.util.WebUtil;
import com.opensymphony.xwork2.ActionSupport;

import java.lang.reflect.Array;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Burst
 * Date: 13-4-17
 * Time: 下午5:12
 * To change this template use File | Settings | File Templates.
 */
public class UpdateService extends BaseService {

    public String doExecute(UpdateActionModel updateActionModel) throws Exception {

        try {

            Map<String, Object> result = updateActionModel.getResultMap();

            if("get_history".equals(updateActionModel.getAction())) {

                result.put("history", bookService.loadHistory(updateActionModel.getBookId()));

            } else if("get_version".equals(updateActionModel.getAction())) {

                BookUpdateRecordDTO last = bookService.loadLastUpdateRecord(updateActionModel.getBookId());
                if(last == null) {
                    result.put("version", "");
                } else {
                    result.put("version", last.getVersion());
                }

            } else if("get_last_append".equals(updateActionModel.getAction())) {

                BookUpdateRecordDTO last = bookService.loadLastUpdateRecord(updateActionModel.getBookId());
                BookUpdateRecordDTO second_to_last = bookService.loadSecondToLastUpdateRecord(updateActionModel.getBookId());;
                BookDTO book = bookService.loadBook(updateActionModel.getBookId());
                result.put("last", last);
                result.put("second_to_last", second_to_last);
                result.put("content", book.getContent().substring(second_to_last.getCurrentSize(), last.getCurrentSize()));

            } else if("get_append_between".equals(updateActionModel.getAction())) {

                BookUpdateRecordDTO from = bookService.loadVersion(updateActionModel.getBookId(), updateActionModel.getFromVersion());
                BookUpdateRecordDTO to = bookService.loadVersion(updateActionModel.getBookId(), updateActionModel.getToVersion());
                BookDTO book = bookService.loadBook(updateActionModel.getBookId());
                result.put("from", from);
                result.put("to", to);
                result.put("content", book.getContent().substring(from.getCurrentSize(), to.getCurrentSize()));

            } else if("append".equals(updateActionModel.getAction())) {

                BookDTO book = bookService.loadBook(updateActionModel.getBookId());
                book.setContent(book.getContent() + updateActionModel.getContentToAppend());
                bookService.updateAndAddRecord(book, WebUtil.getRemoteModel(), updateActionModel.getVersion());

            } else if("append_from_version".equals(updateActionModel.getAction())) {

                BookUpdateRecordDTO from = bookService.loadVersion(updateActionModel.getBookId(), updateActionModel.getFromVersion());
                BookDTO book = bookService.loadBook(updateActionModel.getBookId());
                book.setContent(book.getContent().substring(0, from.getCurrentSize()) + updateActionModel.getContentToAppend());
                bookService.updateAndAddRecord(book, WebUtil.getRemoteModel(), updateActionModel.getVersion());

            } else {
                updateActionModel.setStatus("error");
                updateActionModel.setMessage("unknown action '" + updateActionModel.getAction() + "'.");
                return ActionSupport.SUCCESS;
            }

            updateActionModel.setStatus("success");

        } catch (BookException ex) {
            updateActionModel.setStatus("error");
            updateActionModel.setMessage("book not exists.");
        } catch (Throwable ex) {
            updateActionModel.setStatus("error");
            updateActionModel.setMessage("error occured.");
        }

        return ActionSupport.SUCCESS;
    }
}
