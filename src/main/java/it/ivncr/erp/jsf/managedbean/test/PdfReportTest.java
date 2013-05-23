package it.ivncr.erp.jsf.managedbean.test;


import it.ivncr.erp.service.ServiceFactory;
import it.ivncr.erp.service.report.ReportService;

import java.io.ByteArrayInputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@ManagedBean
@SessionScoped
public class PdfReportTest implements Serializable {

	private static final long serialVersionUID = 1L;

	private static final Logger logger = LoggerFactory.getLogger(PdfReportTest.class);

	public StreamedContent getPdf(int aziendaId) {

		logger.debug("Entering getPdf method.");

		ReportService rs = ServiceFactory.createServiceNoSession("Report");

		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("azienda_id", aziendaId);

		byte[] report = rs.generateReport("reports/test.jasper", parameters);
		ByteArrayInputStream bais = new ByteArrayInputStream(report);

		logger.debug("Report successfully generated.");

		return new DefaultStreamedContent(bais, "application/pdf");
	}
}
