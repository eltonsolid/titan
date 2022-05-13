package br.com.elementi.core.exception;

import br.com.elementi.core.domain.DomainException;


public class NullConstructionException extends DomainException {

	/**
	 *
	 */
	private static final long serialVersionUID = 1667231569185502606L;

	public NullConstructionException(String string) {
		super(string);
	}

}
