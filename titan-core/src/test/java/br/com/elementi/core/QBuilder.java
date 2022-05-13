package br.com.elementi.core;

import java.util.List;

import com.google.common.collect.Lists;

import br.com.elementi.core.tools.Init;

@SuppressWarnings("unchecked")
public class QBuilder<E> {

	protected E entity;

	protected void init() {
		try {
			//Modifica o metodo para aceiutar a instancia do objeto assim podemos passar THIS comoparametros ^^
			this.entity = (E) Init.initializer(entity.getClass());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<E> list() throws Exception {
		return Lists.newArrayList(entity);
	}

	public E get() {
		return entity;
	}

}
