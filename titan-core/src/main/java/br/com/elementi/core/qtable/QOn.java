package br.com.elementi.core.qtable;

import static br.com.elementi.core.qtable.QOperator.EQUAL;

import org.hibernate.Query;

public class QOn implements QConstraint {

	private String aliasOn;
	private String on;
	private String aliasThat;
	private String that;

	public QOn(String aliasOn, String on, String aliasThat, String that) {
		super();
		this.aliasOn = aliasOn;
		this.on = on;
		this.aliasThat = aliasThat;
		this.that = that;
	}

	@Override
	public void joinQuery(StringBuilder query) {
		query.append(aliasOn + "." + on + EQUAL.operator() + aliasThat + "." + that);
	}

	@Override
	public void joinValue(Query query) {
	}

}
