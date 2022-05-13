package br.com.elementi.core.exception;

import br.com.elementi.core.domain.DomainException;

public class NotNullException extends DomainException {

	/**
	 *
	 */
	private static final long serialVersionUID = 2712050236772432970L;

	public NotNullException(String argument) {
		super(argument);
	}

	public NotNullException(String... arguments) {
		super(arguments);
	}

}
