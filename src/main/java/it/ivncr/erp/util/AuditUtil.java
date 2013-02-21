package it.ivncr.erp.util;

import it.ivncr.erp.jsf.managedbean.accesso.session.LoginInfo;

import javax.faces.context.FacesContext;

import org.jboss.logging.MDC;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AuditUtil {

	public static Logger logger = LoggerFactory.getLogger("audit");

	public static void log(Object before, Object after) {

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

		MDC.put("beforeStatus", before.toString());
		MDC.put("afterStatus", after.toString());

		logger.info("");
	}
}
