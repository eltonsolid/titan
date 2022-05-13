package br.com.elementi.core.check;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import br.com.elementi.core.check.Check;
import br.com.elementi.core.exception.CheckException;
import br.com.elementi.test.DummyChecker;

@Ignore
public class CheckTest {

	private DummyChecker dummyChecker;
	private Check check;

	@Before
	public void before() throws Exception {
		dummyChecker = new DummyChecker("TEST");
		check = Check.from();
	}

	@Test
	public void testCheck() throws Exception {
		assertTrue(check.check("test", "TEST").isValid());
	}

	@Test
	public void testCheckFalse() throws Exception {
		assertFalse(check.check("test", "").isValid());
	}

	@Test
	public void testCheckFalseNull() throws Exception {
		String value = null;
		assertFalse(check.check("test", value).isValid());
	}

	@Test
	public void testCheckNumber() throws Exception {
		assertTrue(check.check("test", 0).isValid());
	}

	@Test(expected = CheckException.class)
	public void testFinish() throws Exception {
		check.check("test", "").validate();
	}

	@Test
	public void shoudHaveOneParameter() throws Exception {
		try {
			check.check("test", "").validate();
		} catch (CheckException exception) {
			assertEquals("test", exception.getArgumentList().get(0));
		}
	}

	@Test
	public void shouldHaveThisClass() throws Exception {
		try {
			check.check("test", "").validate();
		} catch (CheckException exception) {
			assertEquals(this.getClass(), exception.getClass());
		}
	}

	@Test
	public void shouldIgnoreOneField() throws Exception {
		check.check(dummyChecker).validate();
	}

	@Test(expected = CheckException.class)
	public void shouldNotIgnoreField() throws Exception {
		dummyChecker.setName("");
		check.check(dummyChecker).validate();
	}
}
