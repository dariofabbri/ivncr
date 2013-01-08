package it.ivncr.erp.service;

public class AlreadyPresentException extends ServiceException {

	private static final long serialVersionUID = 1L;

	public AlreadyPresentException() {
		super();
	}

	public AlreadyPresentException(String message) {
		super(message);
	}

	public AlreadyPresentException(String message, Throwable t) {
		super(message, t);
	}
}
