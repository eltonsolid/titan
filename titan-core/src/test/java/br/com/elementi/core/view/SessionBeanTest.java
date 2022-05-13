package br.com.elementi.core.view;

import static org.junit.Assert.assertEquals;

import org.joda.time.Minutes;
import org.junit.Before;
import org.junit.Test;

public class SessionBeanTest {

	private SessionBean sessionBean;
	private UserLogged logged;

	@Before
	public void before() throws Exception {
		sessionBean = new SessionBean();
		logged = UserLogged.create("TEST", Minutes.THREE);
	}

	@Test
	public void testStartSession() throws Exception {
		sessionBean.startSession(logged);
		assertEquals("TEST", sessionBean.getUserName());
	}

}
