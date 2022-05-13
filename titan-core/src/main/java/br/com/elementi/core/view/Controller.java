package br.com.elementi.core.view;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;

import br.com.elementi.core.check.Check;
import br.com.elementi.core.context.Context;
import br.com.elementi.core.domain.DomainException;
import br.com.elementi.core.tools.Message;
import br.com.elementi.core.tools.Reflect;

public abstract class Controller<SERVICE> {

	@PostConstruct
	public void posConstruction() {
		try {
			configure();
		} catch (DomainException exception) {
			faces().erro((DomainException) exception);
		} catch (Exception exception) {
			exception.printStackTrace();
			faces().erro(exception.getClass().getName() + exception.getMessage());
		}
	}

	public Faces faces() {
		return Faces.create(FacesContext.getCurrentInstance(), Context.getBean(Message.class));
	}

	public abstract void configure() throws Exception;

	public Check check(String key, String value) throws Exception {
		return Check.from().check(key, value);
	}

	public Check check(Object object) throws Exception {
		return Check.from().check(object);
	}

	public SERVICE service() {
		return Context.getBean(Reflect.<SERVICE> getClassDeclarated(getClass()));
	}

	public SessionBean sessionBean() throws Exception {
		return faces().bean(SessionBean.class);
	}

	public <T> T getBean(Class<T> classe) throws Exception {
		return (T) faces().bean(classe);
	}

	public String requestParamter(String name) {
		return FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get(name);
	}

}
