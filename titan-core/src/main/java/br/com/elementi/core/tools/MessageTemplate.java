package br.com.elementi.core.tools;

import java.util.Map;

import br.com.elementi.core.domain.DomainException;

public interface MessageTemplate {

	//TODO este metodo deve ser modificado para ter java 8 com metodo default.



	public Map<String, String> properties();

	public Map<Enum<?>, String> enumerates();

	public Map<Class<? extends DomainException>, String> exceptions();

}
