package it.ivncr.erp.service;

import it.ivncr.erp.mock.MockDataSource;
import it.ivncr.erp.mock.MockInitialContextFactory;
import it.ivncr.erp.service.report.ReportService;

import java.util.HashMap;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ReportServiceTest {

	@Before
	public void setup() throws NamingException {

		System.setProperty(Context.INITIAL_CONTEXT_FACTORY, MockInitialContextFactory.class.getName());

        MockDataSource ds = new MockDataSource();
        ds.setUrl("jdbc:postgresql://localhost:5432/ivncr");
        ds.setUsername("ivncr");
        ds.setPassword("ivncr");

        InitialContext context = new InitialContext();
        MockInitialContextFactory.bind("java:/comp/env", context);
        context.bind("jdbc/ivncr", ds);
	}

	@Test
	public void testTestReport() {

		ReportService rs = ServiceFactory.createServiceNoSession("Report");

		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("azienda_id", 1);

		byte[] report = rs.generateReport("reports/test.jasper", parameters);

		Assert.assertNotNull(report);
		System.out.println(new String(report));
	}
}
