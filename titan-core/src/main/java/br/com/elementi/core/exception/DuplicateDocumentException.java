package br.com.elementi.core.exception;

import br.com.elementi.core.domain.DomainException;
import br.com.elementi.core.model.DocumentType;

public class DuplicateDocumentException extends DomainException {

	/**
	 *
	 */
	private static final long serialVersionUID = 8262187030728306308L;

	public DuplicateDocumentException(DocumentType type, String document) {
		super(type.name(), document);
	}
}
