package br.com.elementi.core.view;

import java.util.Locale;

import javax.el.ELContext;
import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;

import com.sun.faces.el.ManagedBeanELResolver;

import br.com.elementi.core.domain.DomainException;
import br.com.elementi.core.tools.Message;
import br.com.elementi.core.tools.Transform;

public class Faces {

	private static final Severity INFO = FacesMessage.SEVERITY_INFO;
	private static final Severity WARN = FacesMessage.SEVERITY_WARN;
	private static final Severity ERROR = FacesMessage.SEVERITY_ERROR;
	private static final Severity FATAL = FacesMessage.SEVERITY_FATAL ;

	private FacesContext context;
	private Message message;

	private Faces(FacesContext context, Message message) {
		this.context = context;
		this.message = message;
	}

	public static Faces create(FacesContext context, Message message) {
		return new Faces(context, message);
	}

	public ELContext EL() {
		return context.getELContext();
	}

	public Locale locale() {
		return context.getApplication().getDefaultLocale();
	}

	public void info(String key, String... arguments) {
		String messageValue = message.get(key, arguments);
		context.addMessage(null, new FacesMessage(INFO, messageValue, ""));
	}

	public void warn(String key, String... arguments) {
		String messageValue = message.get(key, arguments);
		context.addMessage(null, new FacesMessage(WARN, messageValue, ""));
	}

	public void erro(DomainException exception) {
		String messageValue = message.get(exception);
		context.addMessage(null, new FacesMessage(ERROR, messageValue, ""));
	}

	public void erro(String key, String... arguments){
		String messageValue = message.get(key, arguments);
		context.addMessage(null, new FacesMessage(ERROR, messageValue, ""));
	}

	@SuppressWarnings("unchecked")
	public <T> T bean(Class<T> classe) throws Exception {
		return (T) new ManagedBeanELResolver().getValue(EL(), null, Transform.normalize(classe.getSimpleName()));
	}

}
