package burst.reader.web.action.reader;

import java.io.ByteArrayInputStream;
import java.net.URLEncoder;

import burst.reader.dto.BookDTO;
import burst.reader.web.action.BaseAction;
import burst.reader.web.action.reader.model.DownloadActionModel;

import com.opensymphony.xwork2.ModelDriven;

/**
 * Created with IntelliJ IDEA.
 * User: Burst
 * Date: 13-4-6
 * Time: 下午7:29
 * To change this template use File | Settings | File Templates.
 */
public class DownloadAction extends BaseAction implements ModelDriven<DownloadActionModel> {

	private static final long serialVersionUID = 4093759548509465030L;

	@Override
    public String execute() throws Exception {
        BookDTO book = bookService.getBook(model.getId());
        model.setStream(new ByteArrayInputStream(book.getContent().getBytes("GBK")));
        model.setFileName(URLEncoder.encode(book.getName() + ".txt", "utf-8").replace("+", "%20"));
        return SUCCESS;
    }

    @Override
    public DownloadActionModel getModel() {
        return model;
    }

    public void setModel(DownloadActionModel model) {
        this.model = model;
    }

    private DownloadActionModel model;

}
