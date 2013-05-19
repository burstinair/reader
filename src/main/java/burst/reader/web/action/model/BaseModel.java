package burst.reader.web.action.model;

import burst.web.util.WebUtil;

import java.util.Properties;

public class BaseModel {
	
	private Properties config;

	public Properties getConfig() {
		return config;
	}

	public void setConfig(Properties config) {
		this.config = config;
	}

    private String userAgent = null;

    public String getUserAgent() {
        if(userAgent == null) {
            userAgent = WebUtil.getRemoteModel().getUserAgent();
        }
        return userAgent;
    }
}
