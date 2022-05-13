package br.com.elementi.core.exception;

import br.com.elementi.core.domain.DomainException;

public class NotCreatedException extends DomainException {

	/**
	 *
	 */
	private static final long serialVersionUID = 102068490729493550L;

	public NotCreatedException(String value) {
		super(value);
	}

	public NotCreatedException(DomainException exception, String... arguments) {
		super(exception, arguments);
	}

}
