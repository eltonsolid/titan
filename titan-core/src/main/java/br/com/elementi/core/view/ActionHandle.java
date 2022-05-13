package br.com.elementi.core.view;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import br.com.elementi.core.domain.DomainException;
import br.com.elementi.core.tools.Message;

import com.sun.faces.application.ActionListenerImpl;

public class ActionHandle extends ActionListenerImpl {

	private Message message;

	public ActionHandle(Message message) {
		this.message = message;
	}

	@Override
	public void processAction(ActionEvent event) {
		try {
			NavigationView.navigate(event);
		} catch (Exception exception) {
			handle(exception.getCause() == null ? exception : exception.getCause());
		}
	}

	private void handle(Throwable throwable) {
		try {
			show((Exception) throwable);
		} catch (Exception exception) {
			exception.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, exception.getClass().getName() + exception.getMessage(), ""));
		}

	}

	private void show(Exception exception) throws Exception {
		if (!(exception instanceof DomainException)) {
			throw exception;
		}
		Faces.create(FacesContext.getCurrentInstance(), message).erro((DomainException) exception);
	}
}
