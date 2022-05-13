package br.com.elementi.core.exception;

import br.com.elementi.core.domain.DomainException;

public class NotUniqueFoundException extends DomainException{




	/**
	 *
	 */
	private static final long serialVersionUID = 2928341310756544748L;

	public NotUniqueFoundException(String argument) {
		super(argument);
	}

	public NotUniqueFoundException(String...arguments) {
		super(arguments);
	}

}
