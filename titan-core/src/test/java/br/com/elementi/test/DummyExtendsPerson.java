package br.com.elementi.test;

public class DummyExtendsPerson extends DummyPerson {

	private String extendName;
	private String legalName;

	public DummyExtendsPerson() {
		super();
		this.extendName = "ExetendPerson";
		this.legalName = "LegalName";
	}

	public String getExtendName() {
		return extendName;
	}

	public String getLegalName() {
		return legalName;
	}

}