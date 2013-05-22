package it.ivncr.erp.service.report;

import java.net.URL;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;

import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ReportServiceImpl implements ReportService {

	private static final Logger logger = LoggerFactory.getLogger(ReportServiceImpl.class);

	@Override
	public void setSession(Session session) {

		throw new RuntimeException("No session supported by this service.");
	}

	@Override
	public byte[] generateReport(String report) {

		return generateReport(report, new HashMap<String, Object>());
	}

	@Override
	public byte[] generateReport(String report, Map<String, Object> parameters) {

		logger.debug("Entering generateReport method.");

		byte[] result = null;

		URL url = this.getClass().getClassLoader().getResource(report);
		try {

			// Retrieve database connection using configured pool.
			//
			Context initContext = new InitialContext();
			Context envContext  = (Context)initContext.lookup("java:/comp/env");
			DataSource ds = (DataSource)envContext.lookup("jdbc/ivncr");
			Connection connection = ds.getConnection();
			logger.debug("Retrieved database connection from pool.");

			// Load report.
			//
			JasperReport jr = (JasperReport) JRLoader.loadObject(url);
			logger.debug("Jasper report object loaded.");

			// Inject desired locale parameter.
			//
			parameters.put(JRParameter.REPORT_LOCALE, Locale.ITALIAN);
			parameters.put(JRParameter.REPORT_TIME_ZONE, TimeZone.getTimeZone("Europe/Rome"));

			// Fill report using provided parameters and acquired
			// database connection.
			//
			JasperPrint jp = JasperFillManager.fillReport(
					jr,
					parameters,
					connection);
			logger.debug("Report successfully filled.");

			// Generate PDF.
			//
			result = JasperExportManager.exportReportToPdf(jp);
			logger.debug("Report successfully exported to PDF.");

			// Release JDBC connection.
			//
			connection.close();
			logger.debug("Database connection released.");

		} catch (Exception e) {
			logger.error("Exception caught while generating report.", e);
			throw new RuntimeException("Exception caught while generating report.", e);
		}

		return result;
	}
}