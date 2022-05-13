package br.com.elementi.core.model;

public enum PersonType {
	UNDEFINE(DocumentType.UNDEFINE), FISIC(DocumentType.CPF), JURIDIC(DocumentType.CNPJ);

	private DocumentType documentType;

	private PersonType(DocumentType documentType) {
		this.documentType = documentType;
	}

	public DocumentType getDocumentType() {
		return documentType;
	}

}
