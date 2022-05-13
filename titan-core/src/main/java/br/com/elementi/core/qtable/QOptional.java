package br.com.elementi.core.qtable;

import br.com.elementi.core.tools.Reflect;

public class QOptional {

	private Class<?> entityClass;
	private Object entity;

	public QOptional(Class<?> entityClass, Object entity) {
		this.entityClass = entityClass;
		this.entity = entity;
	}

	public static <T> QOptional of(Class<? extends T> entityClass, T entity) {
		return new QOptional(entityClass, entity);
	}

	public boolean isFound() {
		return entity != null;
	}

	@SuppressWarnings("unchecked")
	public <T> T get() throws Exception {
		return entity == null ? (T) Reflect.instance(entityClass) : (T) entity;
	}

}
