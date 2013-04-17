/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package burst.reader.web.action.reader;

import burst.reader.web.action.reader.service.ReaderService;
import com.opensymphony.xwork2.ModelDriven;

import burst.reader.web.action.BaseAction;
import burst.reader.web.action.reader.model.ReaderActionModel;

/**
 *
 * @author Burst
 */
public class ReaderAction extends BaseAction implements ModelDriven<ReaderActionModel> {
	
	private static final long serialVersionUID = -2511632648336749729L;

    public void setReaderService(ReaderService readerService) {
        this.readerService = readerService;
    }

    private ReaderService readerService;

	public String execute() throws Exception {
        return readerService.doExecute(readerActionModel);
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
