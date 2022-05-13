package br.com.elementi.core.qtable;

import java.util.LinkedList;

import com.google.common.collect.Lists;

public class QSelect implements QQuery {

	private LinkedList<String> projections;
	private boolean distinct;
	private boolean count;

	public QSelect() {
		this.projections = Lists.newLinkedList();
	}

	@Override
	public void joinQuery(StringBuilder query) {
		if (projections.isEmpty()) {
			return;
		}
		query.append(QOperator.SELECT.operator());
		if (distinct) {
			query.append(QOperator.DISTINCT.operator());
		}
		if (count) {
			query.append(QOperator.COUNT.operator());
		}
		for (String projection : projections) {
			query.append(projection + ", ");
		}
		query.deleteCharAt(query.length() - 2);
	}

	public void distinct(String alias) {
		distinct = true;
		project(alias);
	}

	public void count(String alias) {
		count = true;
		project(alias);
	}

	private void project(String alias) {
		if (!projections.contains(alias)) {
			projections.add(alias);
		}
	}

	public void project(String alias, String field) {
		projections.remove(alias);
		project(alias + "." + field);
	}

}
