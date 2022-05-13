package br.com.elementi.core.qtable;

public class QLt extends QAbstract {

	public QLt(String alias, String field, Object value) {
		super(alias, field, value, QOperator.LESSER);
	}

}
