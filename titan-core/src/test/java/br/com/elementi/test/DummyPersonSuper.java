package br.com.elementi.test;

import br.com.elementi.core.domain.DomainEntity;
import br.com.elementi.core.model.Identity;

public class DummyPersonSuper extends DomainEntity {

	private static final String SUPER_NAME = "SuperName";
	private String thatName;

	@Override
	public Identity getId() {
		return null;
	}

	public DummyPersonSuper() {
		thatName = SUPER_NAME;
	}

	public String getThatName() {
		return thatName;
	}

	public void setThatName(String thatName) {
		this.thatName = thatName;
	}

}
