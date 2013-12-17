package it.ivncr.erp.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import org.apache.commons.lang3.text.WordUtils;
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

	public static String makeCommaSeparatedList(List<?> list, String field) {

		StringBuilder sb = new StringBuilder();
		boolean first = true;

		String methodName = "get" + WordUtils.capitalize(field);
		for(Object object : list) {

			try {
				Method getMethod = object.getClass().getMethod(methodName);
				Object result = getMethod.invoke(object);

				if(first) {
					first = false;
				} else {
					sb.append(", ");
				}

				sb.append(result);

			} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {

				String message = String.format("Method %s not found in object %s", methodName, object);
				logger.error(message);
				throw new RuntimeException(message);
			}

		}

		return sb.toString();
	}
}