package br.com.elementi.core.hibernate;

import org.hibernate.HibernateException;
import org.hibernate.boot.model.naming.IllegalIdentifierException;

import br.com.elementi.core.annotation.HibernateTypeResolver;
import br.com.elementi.core.domain.DomainType;

@HibernateTypeResolver
public class BooleanResolver extends DomainType<Boolean, String> {

	@Override
	public Boolean get(Object object) throws HibernateException {
		return Boolean.parseBoolean(object.toString());
	}

	@Override
	public String set(Object object) throws HibernateException {
		if (object instanceof Boolean) {
			Boolean value = (Boolean) object;
			return value ? "true" : "false";
		}

		throw new IllegalIdentifierException(String.valueOf(object));
	}

	@Override
	public String[] getKeys() {
		return new String[] { Boolean.class.getName() };
	}

}
