package br.com.elementi.core.domain;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.usertype.UserType;

import br.com.elementi.core.tools.Reflect;

public abstract class DomainType<GET, SET> implements UserType {

	public int[] sqlTypes() {
		return new int[] { Types.VARCHAR };
	}

	public abstract GET get(Object object) throws HibernateException;

	public abstract SET set(Object object) throws HibernateException;

	public String[] getKeys() {
		return new String[] { this.getClass().getName() }	;
	}

	public Class<?> returnedClass() {
		return Reflect.getClassDeclarated(getClass());
	}

	public boolean equals(Object This, Object That) throws HibernateException {
		return This.equals(That);
	}

	public int hashCode(Object This) throws HibernateException {
		return This.hashCode();
	}

	public Object nullSafeGet(ResultSet rs, String[] names, SessionImplementor session, Object owner)
			throws HibernateException, SQLException {
		return get(rs.getObject((names[0])));
	}

	public void nullSafeSet(PreparedStatement st, Object value, int index, SessionImplementor session)
			throws HibernateException, SQLException {
		SET set = set(value);
		st.setObject(index, set);
	}

	public Object deepCopy(Object value) throws HibernateException {
		return get(value);
	}

	public boolean isMutable() {
		return false;
	}

	public Serializable disassemble(Object value) throws HibernateException {
		return (Serializable) value;
	}

	public Object assemble(Serializable cached, Object owner) throws HibernateException {
		return cached;
	}

	public Object replace(Object original, Object target, Object owner) throws HibernateException {
		return original;
	}

}
