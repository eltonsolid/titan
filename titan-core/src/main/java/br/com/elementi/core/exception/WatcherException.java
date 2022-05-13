package br.com.elementi.core.exception;

import br.com.elementi.core.domain.DomainException;

public class WatcherException extends DomainException {

	/**
	 *
	 */
	private static final long serialVersionUID = 4347203107504830317L;

	public WatcherException(DomainException exception) {
		super(exception);
	}
}
