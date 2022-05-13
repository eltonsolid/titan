package br.com.elementi.core.exception;

import br.com.elementi.core.domain.DomainException;

public class NotExpectedException extends DomainException {

	/**
	 *
	 */
	private static final long serialVersionUID = -1285153446166662679L;

	public NotExpectedException(String argument) {
		super(argument);
	}

	public NotExpectedException(Exception exception, String... arguments) {
		super(arguments);
	}

}
