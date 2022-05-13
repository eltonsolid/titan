package br.com.elementi.core.hibernate;

import java.sql.Types;

import org.hibernate.HibernateException;
import org.hibernate.boot.model.naming.IllegalIdentifierException;

import br.com.elementi.core.domain.DomainType;
import br.com.elementi.core.model.Identity;
import br.com.elementi.core.tools.Reflect;

public class IdentityType extends DomainType<Identity, Integer> {

	private Class<? extends Identity> identity;

	public IdentityType(Class<? extends Identity> identity) {
		this.identity = identity;
	}

	@Override
	public Identity get(final Object object) throws HibernateException {
		try {
			Identity instance = (Identity) Reflect.instance(identity, Integer.parseInt(object.toString().replaceAll("\\D", "")));
			return instance;
		} catch (Exception exception) {
			throw new HibernateException(exception.getMessage());
		}
	}

	@Override
	public Integer set(Object object) throws HibernateException {
		if (object instanceof Identity) {
			Identity identity = (Identity) object;
			return identity.getValue();
		}
		throw new IllegalIdentifierException(String.valueOf(object));
	}

	public int[] sqlTypes() {
		return new int[] { Types.INTEGER };
	}

}
