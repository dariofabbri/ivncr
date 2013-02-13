package it.ivncr.erp.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HibernateUtil {

	private static Logger logger = LoggerFactory.getLogger(HibernateUtil.class);
	
    private static SessionFactory sessionFactory = null;

    public static void setSessionFactory(SessionFactory sessionFactory) {
		HibernateUtil.sessionFactory = sessionFactory;
	}

    public static SessionFactory getSessionFactory() {
    	if(sessionFactory == null)
    		sessionFactory = buildSessionFactory();
    	
        return sessionFactory;
    }

	private static SessionFactory buildSessionFactory() {
    	
        try {
        	Configuration configuration = new Configuration();
        	configuration.configure();
        	ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry(); 
        	SessionFactory factory = configuration.buildSessionFactory(serviceRegistry);
        	
            return factory;
        }
        catch (Throwable e) {
        	
            // Make sure the exception si logged, as it might be swallowed.
        	//
            logger.error("Initial SessionFactory creation failed.", e);
            throw new ExceptionInInitializerError(e);
        }
    }
}