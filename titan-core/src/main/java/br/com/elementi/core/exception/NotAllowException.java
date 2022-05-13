package br.com.elementi.core.exception;

import br.com.elementi.core.domain.DomainException;

public class NotAllowException extends DomainException {

	/**
	 *
	 */
	private static final long serialVersionUID = 862421199191596597L;

	public NotAllowException() {
	}
	
	public NotAllowException(String value) {
		super(value);
	}

	public NotAllowException(DomainException exception, String... arguments) {
		super(exception, arguments);
	}

}
