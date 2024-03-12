/**
 * Created by gram on 23/03/16.
 */
package util;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;


public class HibernateUtil {

    private static final Logger logger = LoggerFactory.getLogger(HibernateUtil.class);
    private static SessionFactory sessionFactory;
    private static ServiceRegistry serviceRegistry;


    private static SessionFactory configureSessionFactory() throws HibernateException {
        if (sessionFactory == null) {
            Configuration configuration = new Configuration().configure();
            serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        }
        return sessionFactory;
    }

    public static SessionFactory getSessionFactory() {
        return configureSessionFactory();

    }
}