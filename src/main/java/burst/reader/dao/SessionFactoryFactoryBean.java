/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package burst.reader.dao;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.springframework.beans.factory.FactoryBean;

/**
 * Hibernate Utility class with a convenient method to get Session Factory
 * object.
 *
 * @author Burst
 */
public class SessionFactoryFactoryBean implements FactoryBean<SessionFactory> {

    private static final SessionFactory sessionFactory;
    
    static {
        try {
            //sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
            String data_dir = System.getenv("OPENSHIFT_DATA_DIR");
            if (data_dir == null || "".equals(data_dir)) {
                data_dir = "d:\\";
            } else if (!data_dir.endsWith("/") && !data_dir.endsWith("\\")) {
                data_dir += '/';
            }
            sessionFactory = new AnnotationConfiguration()
                    .setProperty("hibernate.dialect", "burst.reader.dao.SQLiteDialect")
                    .setProperty("hibernate.connection.driver_class", "org.sqlite.JDBC")
                    .setProperty("hibernate.connection.url", String.format("jdbc:sqlite:%sdata.db", data_dir))
                    .addAnnotatedClass(burst.reader.dto.BookDTO.class)
                    .addAnnotatedClass(burst.reader.dto.BookMarkDTO.class)
                    .buildSessionFactory();
            // Create the SessionFactory from standard (hibernate.cfg.xml) 
            // config file.
            //Configuration configuration = new Configuration();
            //configuration.configure();
            //sessionFactory = configuration.buildSessionFactory(
            //        new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry());
        } catch (Throwable ex) {
            // Log the exception. 
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
    
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

	@Override
	public SessionFactory getObject() throws Exception {
        return sessionFactory;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Class getObjectType() {
		return SessionFactory.class;
	}

	@Override
	public boolean isSingleton() {
		return true;
	}
}
