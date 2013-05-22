package it.ivncr.erp.jsf.managedbean.test;


import it.ivncr.erp.service.ServiceFactory;
import it.ivncr.erp.service.report.ReportService;

import java.io.ByteArrayInputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.commons.lang3.builder.HashCodeBuilder;
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
	
	
	private byte[] getReport(String name, Map<String, Object> parameters) {
		
		
		
	}
	
	public class ReportKey {
		
		private String name;
		private Map<String, Object> parameters;
		
		public ReportKey() {
		}

		public ReportKey(String name, Map<String, Object> parameters) {
			this.name = name;
			this.parameters = parameters;
		}
		
		@Override
		public int hashCode() {
			
			HashCodeBuilder hcb = new HashCodeBuilder(11, 103);
			
			hcb.append(name);
			
			if(parameters != null) {
				for(String key : parameters.keySet()) {
					
				}
			}
			
			return hcb.toHashCode();
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public Map<String, Object> getParameters() {
			return parameters;
		}

		public void setParameters(Map<String, Object> parameters) {
			this.parameters = parameters;
		}
	}
	
	
}
