package it.ivncr.erp.util;

import org.junit.Test;

public class AuditUtilTest {

	@Test
	public void testLog() {

		Object before = new Object();
		Object after = new Object();

		AuditUtil.log(before, after);
	}
}
