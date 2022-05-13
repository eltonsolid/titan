package br.com.elementi.core.domain;

public interface DomainEnum<E extends Enum<E>> {

	public E defaultValue();
}
