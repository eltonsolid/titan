package br.com.elementi.core.domain;

import java.math.BigInteger;
import java.util.List;

import org.hibernate.internal.SessionFactoryImpl;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.elementi.core.qtable.QTable;
import br.com.elementi.core.tools.BuilderEntity;
import br.com.elementi.core.tools.BuilderEntity.BuilderTemplate;
import br.com.elementi.core.tools.Reflect;

public abstract class DomainRepository<T extends BuilderTemplate<? extends DomainEntity>> {

	@Autowired
	private SessionFactoryImpl factory;

	public QTable table() throws Exception {
		return tableOf(Reflect.getClassDeclaratedInside(this.getClass()));
	}

	public T entity() throws Exception {
		return entityOf(Reflect.getClassDeclarated(getClass()));
	}

	public <B extends BuilderTemplate<? extends DomainEntity>> B entityOf(Class<B> builder) throws Exception {
		return BuilderEntity.table(tableOf(Reflect.getClassDeclarated(builder)), builder);
	}

	public QTable tableOf(Class<?> clazz) throws Exception {
		return QTable.of(clazz, factory.openSession());
	}

	public <E extends DomainEntity> void saveEntity(E e) {
		factory.getCurrentSession().save(e);
	}

	public <E extends DomainEntity> void updateEntity(E e) {
		factory.getCurrentSession().update(e);
	}

	@SuppressWarnings("unchecked")
	public <E extends DomainEntity> List<E> list(String query) {
		return factory.getCurrentSession().createQuery(query).list();
	}

	public Integer nextSequeceValue(String sequenceName) {
		String sql = factory.getDialect().getSequenceNextValString(sequenceName);
		return ((BigInteger) factory.getCurrentSession().createSQLQuery(sql).list().get(0)).intValue();
	}

}
