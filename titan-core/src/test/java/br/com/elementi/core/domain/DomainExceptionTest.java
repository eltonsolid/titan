package br.com.elementi.core.domain;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import br.com.elementi.core.Unit;
import br.com.elementi.core.exception.NotAllowException;
import br.com.elementi.core.exception.NotNullException;

public class DomainExceptionTest extends Unit {

	private DomainException domainException;

	@Before
	public void before() throws Exception {
		domainException = new DomainException(new NotAllowException(NAME), DESCRIPTION_1, DESCRIPTION_2) {
			private static final long serialVersionUID = 3317424032980722446L;
		};
	}

	@Test
	public void testGetArguments() throws Exception {
		assertEquals(DESCRIPTION_1, domainException.getArguments()[0]);
	}

	@Test
	public void testGetSecondArguments() throws Exception {
		assertEquals(DESCRIPTION_2, domainException.getArguments()[1]);
	}

	@Test
	public void testGetMessage() throws Exception {
		assertEquals(DESCRIPTION_1 + " - " + DESCRIPTION_2, domainException.getMessage());
	}

	@Test
	public void testGetMessageEmpty() throws Exception {
		domainException = new DomainException() {
			private static final long serialVersionUID = 3317424032980722446L;
		};
		assertEquals("", domainException.getMessage());
	}

	@Test
	public void testGetDownException() throws Exception {
		assertTrue(domainException.getDownException().getClass().isAssignableFrom(NotAllowException.class));
	}

	@Test
	public void testGetDownExceptionForThis() throws Exception {
		domainException = new DomainException() {
			private static final long serialVersionUID = 3317424032980722446L;
		};
		assertTrue(domainException.getDownException().getClass().equals(domainException.getClass()));
	}

	@Test
	public void testToString() throws Exception {
		assertEquals("NotNullException (" + DESCRIPTION_1 + " - " + DESCRIPTION_2 + ")", new NotNullException(
				DESCRIPTION_1, DESCRIPTION_2).toString());
	}

	@Test
	public void testHasNext() throws Exception {
		assertTrue(domainException.hasNext());
	}

	@Test
	public void testHasNotNext() throws Exception {
		assertFalse(new NotNullException().hasNext());
	}

	@Test
		public void testGetStack() throws Exception {
			String stack = new NotAllowException(new NotNullException(NAME), DESCRIPTION_1, DESCRIPTION_2).getStack();
			assertEquals("NotAllowException (DESCRIPTION_1 - DESCRIPTION_2) :: NotNullException (NAME)", stack);
		}

}
