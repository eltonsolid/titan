package br.com.elementi.test;

import br.com.elementi.core.annotation.ReflectIgnore;

public class DummyChecker {

	private String name;

	@ReflectIgnore
	private String nameIgnored;

	public DummyChecker(String name) {
		super();
		this.name = name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public String getNameIgnored() {
		return nameIgnored;
	}

	public void setNameIgnored(String nameIgnored) {
		this.nameIgnored = nameIgnored;
	}

}
