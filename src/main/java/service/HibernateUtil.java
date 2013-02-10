/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.AnnotationConfiguration;

/**
 * Hibernate Utility class with a convenient method to get Session Factory
 * object.
 *
 * @author Burst
 */
public class HibernateUtil {

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
                    .setProperty("hibernate.dialect", "service.SQLiteDialect")
                    .setProperty("hibernate.connection.driver_class", "org.sqlite.JDBC")
                    .setProperty("hibernate.connection.url", String.format("jdbc:sqlite:%sdata.db", data_dir))
                    .addAnnotatedClass(model.Book.class)
                    .addAnnotatedClass(model.BookMark.class)
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
}
