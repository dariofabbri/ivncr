package it.ivncr.erp.service;

import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AbstractService {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	protected Session session;
	
	public void setSession(Session session) {
		this.session = session;
	}

}
