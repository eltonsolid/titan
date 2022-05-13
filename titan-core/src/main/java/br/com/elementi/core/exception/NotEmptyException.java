package br.com.elementi.core.exception;

import br.com.elementi.core.domain.DomainException;

public class NotEmptyException extends DomainException {

	/**
	 *
	 */
	private static final long serialVersionUID = -4304039901437402605L;

	public NotEmptyException(String argument) {
		super(argument);
	}

	public NotEmptyException(String... arguments) {
		super(arguments);
	}
}
