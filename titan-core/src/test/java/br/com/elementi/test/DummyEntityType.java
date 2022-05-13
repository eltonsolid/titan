package br.com.elementi.test;

import br.com.elementi.core.domain.DomainEnum;

public enum DummyEntityType implements DomainEnum<DummyEntityType> {

	UNDEFINE, FIRST, SECOND;

	private DummyEntityType() {
		System.out.println("DummyEntityType.DummyEntityType()");
	}

	public DummyEntityType defaultValue() {
		return UNDEFINE;
	}

}
