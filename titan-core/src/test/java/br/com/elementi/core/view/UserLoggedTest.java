package br.com.elementi.core.view;

import static org.junit.Assert.*;

import org.joda.time.Minutes;
import org.junit.Before;
import org.junit.Test;

public class UserLoggedTest {

	private UserLogged login;

	@Before
	public void before() throws Exception {
		login = UserLogged.create("Eltonsolid", Minutes.minutes(1));
	}

	@Test
	public void testGetUser() throws Exception {
		assertEquals("Eltonsolid", login.getUser());
	}

	@Test
	public void testIsTimeout() throws Exception {
		assertFalse(login.isTimeout());
	}

	@Test
	public void testIsTimeoutFalse() throws Exception {
		UserLogged loginTimeout = UserLogged.create("Eltonsolid", Minutes.MIN_VALUE);
		assertTrue(loginTimeout.isTimeout());
	}


}
