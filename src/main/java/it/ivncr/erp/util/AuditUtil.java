package it.ivncr.erp.util;

import it.ivncr.erp.jsf.managedbean.accesso.session.LoginInfo;

import javax.faces.context.FacesContext;

import org.jboss.logging.MDC;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AuditUtil {

	public static Logger logger = LoggerFactory.getLogger("audit");


	public enum Operation {
		Create,
		Update,
		Delete
	}


	public enum Snapshot {
		Source,
		Destination
	}


	public static void log(Operation operation, Snapshot snapshot, Object status) {

		// A few parameters combinations are not allowed. A create operation can only support
		// a destination snapshot, while a delete only gets a source snapshot.
		//
		if(operation == Operation.Create && snapshot == Snapshot.Source) {
			throw new IllegalArgumentException("Unsupported combination of parameters. Operation Create only support Destination snapshot.");
		}
		if(operation == Operation.Delete && snapshot == Snapshot.Destination) {
			throw new IllegalArgumentException("Unsupported combination of parameters. Operation Delete only support Source snapshot.");
		}

		MDC.put("operation", operation);

		StackTraceElement[] trace = Thread.currentThread().getStackTrace();
		MDC.put("serviceClass", trace[2].getClassName());
		MDC.put("serviceMethod", trace[2].getMethodName());
		MDC.put("serviceLineNumber", trace[2].getLineNumber());

		String logonUser = null;
		FacesContext context = FacesContext.getCurrentInstance();
		if(context != null) {
			LoginInfo loginInfo = context.getApplication().evaluateExpressionGet(context, "#{loginInfo}", LoginInfo.class);
			if(loginInfo != null) {
				logonUser = loginInfo.getUsername();
			}
		}
		MDC.put("logonUser", logonUser);

		MDC.put("snapshot", snapshot);

		MDC.put("status", status != null ? status.toString() : null);

		logger.info("");
	}
}
