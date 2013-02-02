package it.ivncr.erp.util;

import java.io.IOException;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FacesUtil {

	private static final Logger logger = LoggerFactory.getLogger(FacesUtil.class);

	public static void redirectToHome(boolean keepMessages) {

		ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();

		String contextName = ec.getRequestContextPath();

		try {

			ec.getFlash().setKeepMessages(keepMessages);
			ec.redirect(String.format("%s/faces/index.xhtml", contextName));

		} catch (IOException e) {
			String msg = "Exception caught while redirecting flow to home page.";
			logger.error(msg, e);
			throw new RuntimeException(msg, e);
		}

	}

}
