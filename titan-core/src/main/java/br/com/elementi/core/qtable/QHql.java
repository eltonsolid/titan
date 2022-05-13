package br.com.elementi.core.qtable;

import org.hibernate.Query;

public class QHql{

	public final String hql;

	public QHql(String value) {
		this.hql = value;
	}

	public void validateQuery(QFrom from) throws Exception {
	}

	public void joinQuery(StringBuilder query) {
		query.append(QOperator.alias(hql).trim());
	}

	public void joinValue(Query query) {

	}

}
