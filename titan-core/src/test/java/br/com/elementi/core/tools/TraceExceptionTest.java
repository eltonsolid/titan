package br.com.elementi.core.tools;

import static org.junit.Assert.*;

import org.junit.Ignore;
import org.junit.Test;

import br.com.elementi.core.exception.NotNullException;

public class TraceExceptionTest {

	@Test(expected = NotNullException.class)
	public void testThrowable() throws Exception {
		TraceException.throwable(NotNullException.class);
	}

	@Ignore
	@Test
	public void testThrowableMethodName() throws Exception {
		try {
			TraceException.throwable(NotNullException.class);
		} catch (NotNullException exception) {
			assertTrue(exception.getMessage().contains("testThrowableMethodName"));
		}
	}

}
