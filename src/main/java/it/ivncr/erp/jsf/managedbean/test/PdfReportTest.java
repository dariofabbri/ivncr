package it.ivncr.erp.jsf.managedbean.test;


import it.ivncr.erp.service.ServiceFactory;
import it.ivncr.erp.service.report.ReportService;

import java.io.ByteArrayInputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;


@ManagedBean
@ViewScoped
public class PdfReportTest implements Serializable {

	private static final long serialVersionUID = 1L;

	private StreamedContent pdf;

	@PostConstruct
	public void init() {

		ReportService rs = ServiceFactory.createServiceNoSession("Report");

		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("azienda_id", 1);

		byte[] report = rs.generateReport("reports/test.jasper", parameters);

		ByteArrayInputStream bais = new ByteArrayInputStream(report);
		pdf = new DefaultStreamedContent(bais, "application/pdf");
	}

	public StreamedContent getPdf() {
		return pdf;
	}

	public void setPdf(StreamedContent pdf) {
		this.pdf = pdf;
	}
}
