package br.com.elementi.core.hibernate;

import java.util.Date;

import org.hibernate.HibernateException;
import org.joda.time.DateTime;

import br.com.elementi.core.annotation.HibernateTypeResolver;
import br.com.elementi.core.domain.DomainType;

@HibernateTypeResolver
public class DateTimeResolver extends DomainType<DateTime, Date> {

	@Override
	public DateTime get(Object object) throws HibernateException {
		return new DateTime(object);
	}

	@Override
	public Date set(Object object) throws HibernateException {
		return ((DateTime) object).toDate();
	}

	@Override
	public String[] getKeys() {
		return new String[] { DateTime.class.getName() };
	}
}
