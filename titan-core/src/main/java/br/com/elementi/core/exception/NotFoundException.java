package br.com.elementi.core.exception;

import br.com.elementi.core.domain.DomainException;

public class NotFoundException extends DomainException{




	/**
	 *
	 */
	private static final long serialVersionUID = 2928341310756544748L;

	public NotFoundException(String argument) {
		super(argument);
	}

	public NotFoundException(String...arguments) {
		super(arguments);
	}

}
