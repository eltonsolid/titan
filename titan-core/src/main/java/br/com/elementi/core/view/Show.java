package br.com.elementi.core.view;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;

import br.com.elementi.core.tools.Message;

public class Show {

	private static final Severity INFO = FacesMessage.SEVERITY_INFO;
	private static final Severity WARN = FacesMessage.SEVERITY_WARN;
	private static final Severity ERROR = FacesMessage.SEVERITY_ERROR;

	private FacesContext facesContext;
	private Message message;

	private Show(FacesContext facesContext, Message message) {
		this.facesContext = facesContext;
		this.message = message;
	}

	public static Show create(FacesContext facesContext, Message message) {
		return new Show(facesContext, message);
	}

	public void info(String key, String... arguments) throws Exception {
		show(INFO, internationalize(key, arguments));
	}

	public void warn(String key, String... arguments) throws Exception {
		show(WARN, internationalize(key, arguments));
	}

	public void erro(String key, String... arguments) throws Exception {
		show(ERROR, internationalize(key, arguments));
	}


	private void show(Severity severity, String value) {
		facesContext.addMessage(null, new FacesMessage(severity, value, ""));
	}

	private String internationalize(String key, String... arguments) throws Exception {
		return message.get(key, arguments);
	}

}
