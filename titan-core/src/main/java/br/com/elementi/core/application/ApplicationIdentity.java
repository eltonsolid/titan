package br.com.elementi.core.application;

import br.com.elementi.core.annotation.IdentityDefine;
import br.com.elementi.core.model.Identity;

@IdentityDefine
public class ApplicationIdentity extends Identity {

	/**
	 *
	 */
	private static final long serialVersionUID = -6076187710356512677L;


	private ApplicationIdentity(Integer id) {
		super(id);
	}

	public static ApplicationIdentity create(Integer id) {
		return new ApplicationIdentity(id);
	}

}
