package br.com.elementi.core.view;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class SessionBean {

	private UserLogged logged;
	private Object transfer;

	public Object getTransfer() {
		return transfer;
	}

	public void setTransfer(Object transfer) {
		this.transfer = transfer;
	}

	public void startSession(UserLogged logged) {
		this.logged = logged;
	}

	public String getUserName() {
		return logged.getUser();
	}

}
