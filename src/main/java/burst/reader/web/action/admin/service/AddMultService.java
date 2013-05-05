package burst.reader.web.action.admin.service;

import burst.reader.dto.BookDTO;
import burst.reader.web.action.admin.model.AddMultActionModel;
import burst.reader.web.action.service.BaseService;
import burst.web.util.WebUtil;
import com.opensymphony.xwork2.ActionSupport;
import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.model.FileHeader;

import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Burst
 * Date: 13-5-5
 * Time: 下午2:21
 * To change this template use File | Settings | File Templates.
 */
public class AddMultService extends BaseService {

    public String doExecute(AddMultActionModel model) throws Exception {
        return ActionSupport.SUCCESS;
    }

    public String doSubmit(AddMultActionModel model) throws Exception {

        model.setSuccess(true);

        try {
            ZipFile zipFile = new ZipFile(model.getUpload());
            zipFile.setFileNameCharset("GBK");
            for(FileHeader header : (List<FileHeader>)zipFile.getFileHeaders()) {
                if(!header.isDirectory()) {
                    String[] tags = header.getFileName().split("[\\\\/]");
                    String content = WebUtil.readAllText(zipFile.getInputStream(header));

                    BookDTO book = new BookDTO();
                    book.setAuthor(tags[0]);
                    book.setContent(content);
                    book.setAddDate(new Date());
                    book.setVisible(BookDTO.VISIBLE);
                    if(tags[1].toLowerCase().endsWith(".txt")) {
                        book.setName(tags[1].substring(0, tags[1].length() - 4));
                    } else {
                        book.setName(tags[1]);
                    }

                    bookService.addBookAndAddRecord(book, WebUtil.getRemoteModel(), model.getVersion());
                }
            }
        } catch (Exception ex) {
            model.setSuccess(false);
            throw ex;
        }
        return ActionSupport.SUCCESS;
    }
}
