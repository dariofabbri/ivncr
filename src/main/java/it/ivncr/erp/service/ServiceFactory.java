package it.ivncr.erp.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ServiceFactory {

	public static final Logger logger = LoggerFactory.getLogger(ServiceFactory.class);
	
	public static <T extends Service> T createService(Class<T> implementation, Class<T> iface) {
		
		T proxied = null;
		try {
			proxied = implementation.newInstance();
		} catch (Exception e) {
			String message = String.format("Exception caught while creating instance of service implementation %s.", implementation.toString());
			logger.error(message, e);
			throw new RuntimeException(message, e);
		}
		
		T service = SessionDecorator.<T>createProxy(proxied, iface);
		return service;
	}
	
	@SuppressWarnings("unchecked")
	public static <T extends Service> T createService(String name) {
		
		Package baseServicePackage = ServiceFactory.class.getPackage();
		
		String serviceInterfaceName = String.format("%s.%s.%sService",
				baseServicePackage.getName(),
				name.toLowerCase(),
				name);
		
		Class<T> serviceInterface = null;
		try {
			serviceInterface = (Class<T>) Class.forName(serviceInterfaceName);
		} catch (ClassNotFoundException e) {
			String message = String.format("Exception caught while accessing service interface %s by reflection.", serviceInterfaceName);
			logger.error(message, e);
			throw new RuntimeException(message, e);
		}
		
		if(!Service.class.isAssignableFrom(serviceInterface)) {
			String message = String.format("Specified service interface %s does not implement Service interface.", serviceInterfaceName);
			logger.error(message);
			throw new RuntimeException(message);
		}
		
		
		String serviceImplementationName = String.format("%s.%s.%sServiceImpl",
				baseServicePackage.getName(),
				name.toLowerCase(),
				name);
		
		Class<T> serviceImplementation = null;
		try {
			serviceImplementation = (Class<T>) Class.forName(serviceImplementationName);
		} catch (ClassNotFoundException e) {
			String message = String.format("Exception caught while accessing service implementation %s by reflection.", serviceImplementationName);
			logger.error(message, e);
			throw new RuntimeException(message, e);
		}
		
		if(!serviceInterface.isAssignableFrom(serviceImplementation)) {
			String message = String.format("Specified service implementation %s does not implement the corresponding interface %s.", serviceImplementationName, serviceInterfaceName);
			logger.error(message);
			throw new RuntimeException(message);
		}
		
		return createService(serviceImplementation, serviceInterface);
	}

	/*
	 * Before reimplementing the factory using reflection, the static methods were like
	 * this one.
	 *
	public static LUTService createLUTService() {

		LUTService service = SessionDecorator.<LUTService>createProxy(new LUTServiceImpl(), LUTService.class);
		return service;
	}
	*/
}