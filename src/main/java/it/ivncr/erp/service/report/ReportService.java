package it.ivncr.erp.service.report;

import it.ivncr.erp.service.Service;

import java.util.Map;

public interface ReportService extends Service {

	public byte[] generateReport(String report);

	public byte[] generateReport(String report, Map<String, Object> parameters);
}