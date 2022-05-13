package br.com.elementi.test;

import br.com.elementi.core.model.Identity;

public class DummyIdentityEntity extends Identity {

	private DummyIdentityEntity(Integer id) {
		super(id);
	}
	
	public static DummyIdentityEntity create(Integer id) {
		return new DummyIdentityEntity(id);
	}

}
