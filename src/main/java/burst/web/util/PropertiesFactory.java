package burst.web.util;

import java.io.BufferedInputStream;
import java.util.Properties;

import org.springframework.beans.factory.FactoryBean;

public class PropertiesFactory implements FactoryBean<Properties> {

	private String path;
	
	@Override
	public Properties getObject() throws Exception {
		Properties res = new Properties();
		BufferedInputStream stream = new BufferedInputStream(PropertiesFactory.class.getResourceAsStream("/" + path));
		res.load(stream);
		stream.close();
		return res;
	}

	@Override
	public Class getObjectType() {
		return Properties.class;
	}

	@Override
	public boolean isSingleton() {
		return false;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getPath() {
		return path;
	}

}
