package it.ivncr.erp.service;

import it.ivncr.erp.model.commerciale.Divisa;
import it.ivncr.erp.service.lut.LUTService;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class LUTServiceTest extends BaseServiceTest {
	
	@Before
	public void warmUp() {

		LUTService ls = ServiceFactory.createService("LUT");
		List<Divisa> list = ls.listItems("Divisa");
		Assert.assertNotNull(list);
	}
	
	@Test
	public void testListItems() {

		for(int i = 0; i < 10000; ++i) {
			LUTService ls = ServiceFactory.createService("LUT");
			
			List<Divisa> list = ls.listItems("Divisa");
			
			Assert.assertNotNull(list);
			System.out.println(list);
			System.out.println(list.size());
		}
	}
}
