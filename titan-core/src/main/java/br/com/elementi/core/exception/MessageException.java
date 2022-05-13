package br.com.elementi.core.exception;

import br.com.elementi.core.domain.DomainException;

public class MessageException extends DomainException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5446423745950829201L;

	public MessageException(String... arguments) {
		super(arguments);
	}

}
