package br.com.elementi.core.tools;

import static org.junit.Assert.*;

import org.junit.Test;

import br.com.elementi.core.exception.NotEmptyException;
import br.com.elementi.core.tools.Transform;

public class TransformTest {

	@Test
	public void testCaptalize() throws Exception {
		String value = "name";
		String transfomed = Transform.capitalize(value);
		assertEquals("Name", transfomed);
	}

	@Test(expected = NotEmptyException.class)
	public void testCaptalizeOnEmplty() throws Exception {
		Transform.capitalize("");
	}

}
