package br.com.elementi.core.qtable;

import static br.com.elementi.core.qtable.QOperator.ALIAS;
import static br.com.elementi.core.qtable.QOperator.FROM;

import java.util.LinkedList;

import br.com.elementi.core.tools.Transform;
import com.google.common.collect.Lists;

import br.com.elementi.core.annotation.QTableNotFoundException;
import br.com.elementi.core.domain.DomainException;
import br.com.elementi.core.exception.NotFoundException;
import br.com.elementi.core.exception.NotUniqueFoundException;
import br.com.elementi.core.tools.Reflect;

public class QFrom implements QQuery {

	private LinkedList<Production> productions;

	private class Production {
		public Production(Class<?> from, String alias) {
			this.from = from;
			this.alias = alias;
		}

		public Class<?> from;
		public String alias;
	}

	public QFrom(Class<?> from) throws Exception {
		this.productions = Lists.newLinkedList();
		add(from);
	}

	private boolean add(Class<?> from) throws Exception {
		return this.productions.add(new Production(from, Transform.normalize(from.getSimpleName())));
	}

	public Class<?> entityClass() {
		return base();
	}

	private Class<?> base() {
		return productions.getFirst().from;
	}

	public void join(Class<?> classe) throws Exception {
		add(classe);
	}

	@Override
	public void joinQuery(StringBuilder query) {
		query.append(FROM.operator());
		productions.forEach(p -> query.append(p.from.getSimpleName() + " " + p.alias + ", "));
		query.setLength(query.length() - 2);
	}

	public DomainException notFoundException() throws Exception {
		if (base().isAnnotationPresent(QTableNotFoundException.class)) {
			DomainException instance = (DomainException) Reflect.instance(base().getAnnotation(QTableNotFoundException.class).value());
			return instance;
		}
		return new NotFoundException(base().getSimpleName());
	}

	public DomainException notUniqueFoundException() throws Exception {
		return new NotUniqueFoundException(base().getSimpleName());
	}

	public String baseAlias() {
		return productions.getFirst().alias;
	}

	public String currentAlias() {
		return productions.getLast().alias;
	}

	public String previousAlias() {
		return productions.get(productions.size()).alias;
	}

}
