package burst.reader.web.action.admin.model;

import java.util.List;

import burst.reader.web.action.model.BaseModel;

public class ExecuteSQLActionModel extends BaseModel {

    private String queryString;
    public String getQueryString() {
        return queryString;
    }
    public void setQueryString(String value) {
        queryString = value;
    }
    
    private String nonQuery;
	public String getNonQuery() {
		return nonQuery;
	}
	public void setNonQuery(String nonQuery) {
		this.nonQuery = nonQuery;
	}
	
    private String query;
	public String getQuery() {
		return query;
	}
	public void setQuery(String query) {
		this.query = query;
	}

    public void setResultStatus(int resultStatus) {
        this.resultStatus = resultStatus;
    }

    public int getResultStatus() {
        return resultStatus;
    }

    private int resultStatus;
	
	private List<List<Object>> resultRows;
	public List<List<Object>> getResultRows() {
		return resultRows;
	}
	public void setResultRows(List<List<Object>> resultRows) {
		this.resultRows = resultRows;
	}
}