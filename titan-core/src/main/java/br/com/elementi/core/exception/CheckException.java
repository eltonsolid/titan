package br.com.elementi.core.exception;

import java.util.LinkedList;

import br.com.elementi.core.domain.DomainException;

public class CheckException extends DomainException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7313980370812225361L;

	public CheckException(LinkedList<String> checkeds) {
		super(checkeds.toArray(new String[] {}));
	}

}
