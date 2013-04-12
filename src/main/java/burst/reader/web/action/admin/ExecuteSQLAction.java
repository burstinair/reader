/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package burst.reader.web.action.admin;

import burst.reader.service.ExecuteSQLService;
import com.opensymphony.xwork2.ModelDriven;

import burst.reader.web.action.BaseAction;
import burst.reader.web.action.admin.model.ExecuteSQLActionModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Burst
 */
public class ExecuteSQLAction extends BaseAction implements ModelDriven<ExecuteSQLActionModel> {

	private static final long serialVersionUID = 8881556805633489846L;

    private ExecuteSQLService executeSQLService;

    public void setExecuteSQLService(ExecuteSQLService executeSQLService) {
        this.executeSQLService = executeSQLService;
    }

	public String execute() throws Exception {

        if(executeSQLActionModel.getQueryString() == null) {
            return SUCCESS;
        }

		if(executeSQLActionModel.getQuery() != null) {
            List<Map> rawResult = executeSQLService.execute(executeSQLActionModel.getQueryString());
            List<List<Object>> result = new ArrayList<List<Object>>();
            if(!rawResult.isEmpty()) {
                List<Object> header = new ArrayList<Object>();
                for(Object entry : rawResult.get(0).entrySet()) {
                    header.add(((Map.Entry)entry).getKey());
                }
                result.add(header);
                for(Map map : rawResult) {
                    List<Object> row = new ArrayList<Object>();
                    for(Object entry : map.entrySet()) {
                        row.add(((Map.Entry)entry).getValue());
                    }
                    result.add(row);
                }
            }
            executeSQLActionModel.setResultRows(result);
		} else {
			executeSQLActionModel.setResultStatus(executeSQLService.executeNonQuery(executeSQLActionModel.getQueryString()));
		}
		
        return SUCCESS;
    }

    private ExecuteSQLActionModel executeSQLActionModel;

    @Override
	public ExecuteSQLActionModel getModel() {
		return executeSQLActionModel;
	}
    
	public void setExecuteSQLActionModel(ExecuteSQLActionModel executeSQLActionModel) {
		this.executeSQLActionModel = executeSQLActionModel;
	}
}
