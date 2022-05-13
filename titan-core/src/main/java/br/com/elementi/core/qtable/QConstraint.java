package br.com.elementi.core.qtable;

import org.hibernate.Query;

public interface QConstraint extends QQuery {

	public void joinValue(Query query);

}
