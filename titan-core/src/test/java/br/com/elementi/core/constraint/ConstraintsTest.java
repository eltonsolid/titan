package br.com.elementi.core.constraint;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import br.com.elementi.core.Unit;
import br.com.elementi.core.exception.NotAllowException;
import br.com.elementi.core.exception.NotEmptyException;
import br.com.elementi.core.exception.NotNullException;

import com.google.common.collect.Lists;

public class ConstraintsTest extends Unit {

	@Test(expected = NotNullException.class)
	public void testNotNull() throws Exception {
		Constraints.notNull(null);
	}

	@Test
	public void testNotNullWithValue() throws Exception {
		String value = Constraints.notNull(NAME);
		assertEquals(NAME, value);
	}

	@Test(expected = NotEmptyException.class)
	public void testNotEmpty() throws Exception {
		Constraints.notEmpty("");
	}

	@Test
	public void testNotEmptyWithString() throws Exception {
		String name = Constraints.notEmpty(NAME);
		assertEquals(NAME, name);
	}

	@Test(expected = NotEmptyException.class)
	public void testNotEmptyWithList() throws Exception {
		Constraints.notEmpty(EMPTY_LIST);
	}

	@Test
	public void testNotEmptyWithListFiled() throws Exception {
		List<?> list = Constraints.notEmpty(Lists.newArrayList(""));
		assertEquals(ONE.intValue(), list.size());
	}

	@Test(expected = NotNullException.class)
	public void testNotEmptyFail() throws Exception {
		String value = null;
		Constraints.notEmpty(value);
	}

	@Test(expected = NotAllowException.class)
	public void testNotAllow() throws Exception {
		Constraints.notAllow(Boolean.TRUE);
	}

	@Test
	public void testNotAllowFalse() throws Exception {
		Constraints.notAllow(Boolean.FALSE);
	}

}
