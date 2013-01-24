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
		
		// It is assumed that the ServiceFactory is located at the root of the service
		// portion of the packages hierarchy. It is therefore used to retrieve
		// the base package name.
		//
		Package baseServicePackage = ServiceFactory.class.getPackage();
		
		// The service interface name is created using the convention that it is contained in
		// a package directly below the base service package named as the service itself and that 
		// its name is the name of the service plus Service suffix.
		//
		String serviceInterfaceName = String.format("%s.%s.%sService",
				baseServicePackage.getName(),
				name.toLowerCase(),
				name);
		
		// Check if the service interface can be found by the classloader.
		//
		Class<T> serviceInterface = null;
		try {
			serviceInterface = (Class<T>) Class.forName(serviceInterfaceName);
		} catch (ClassNotFoundException e) {
			String message = String.format("Exception caught while accessing service interface %s by reflection.", serviceInterfaceName);
			logger.error(message, e);
			throw new RuntimeException(message, e);
		}
		
		// Check that the found service interface is really an interface.
		//
		if(!serviceInterface.isInterface()) {
			String message = String.format("Specified service interface %s is not an interface!", serviceInterfaceName);
			logger.error(message);
			throw new RuntimeException(message);
		}
		
		// Check that the found interface extends the Service interface.
		//
		if(!Service.class.isAssignableFrom(serviceInterface)) {
			String message = String.format("Specified service interface %s does not extend Service interface.", serviceInterfaceName);
			logger.error(message);
			throw new RuntimeException(message);
		}

		
		// The implementation class name is built with a convention similar to that of the service
		// interface, but using the ServiceImpl suffix.
		//
		String serviceImplementationName = String.format("%s.%s.%sServiceImpl",
				baseServicePackage.getName(),
				name.toLowerCase(),
				name);
		
		// Retrieve the class using the classloader.
		//
		Class<T> serviceImplementation = null;
		try {
			serviceImplementation = (Class<T>) Class.forName(serviceImplementationName);
		} catch (ClassNotFoundException e) {
			String message = String.format("Exception caught while accessing service implementation %s by reflection.", serviceImplementationName);
			logger.error(message, e);
			throw new RuntimeException(message, e);
		}
		
		// Check that the implementation class implements the corresponding service interface.
		//
		if(!serviceInterface.isAssignableFrom(serviceImplementation)) {
			String message = String.format("Specified service implementation %s does not implement the corresponding interface %s.", serviceImplementationName, serviceInterfaceName);
			logger.error(message);
			throw new RuntimeException(message);
		}
		
		// Create the service by injecting the Hibernate session using the decorator.
		//
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