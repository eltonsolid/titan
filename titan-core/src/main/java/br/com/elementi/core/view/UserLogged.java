package br.com.elementi.core.view;

import org.joda.time.DateTime;
import org.joda.time.Minutes;

public class UserLogged {

	private String user;
	private Minutes timeout;
	private DateTime lastAccess;

	public UserLogged(String user, Minutes timeout, DateTime lastAccess) {
		super();
		this.user = user;
		this.timeout = timeout;
		this.lastAccess = lastAccess;
	}

	public static UserLogged create(String user, Minutes timeout) {
		return new UserLogged(user, timeout, DateTime.now());
	}

	public String getUser() {
		return user;
	}

	public boolean isTimeout() {
		Minutes minutes = Minutes.minutesBetween(lastAccess, DateTime.now());
		return minutes.isGreaterThan(timeout);
	}

	public void updateLastAccess() {
		this.lastAccess = DateTime.now();
	}

}
