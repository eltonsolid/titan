package br.com.elementi.core.qtable;

import static br.com.elementi.core.qtable.QOperator.FETCH;

public class QFetch implements QQuery {

	private String alias;
	private String field;

	public QFetch(String alias, String field) {
		this.alias = alias;
		this.field = field;
	}

	public void joinQuery(StringBuilder query) {
		query.append(FETCH.operator() + alias + "." + field);
	}

}
