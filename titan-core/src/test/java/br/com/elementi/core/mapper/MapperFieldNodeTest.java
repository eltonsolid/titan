package br.com.elementi.core.mapper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class MapperFieldNodeTest {

	private static final String NAME = "name";
	private MapperNode field;

	@Before
	public void before() throws Exception {
		field = MapperNode.onGet(NAME);
	}

	@Test
	public void testValue() throws Exception {
		assertEquals(NAME, field.name());
	}

	@Test
	public void testNameForGet() throws Exception {
		assertEquals("getName", field.nameForGet());
	}

	@Test
	public void testNameForGetInformed() throws Exception {
		field = MapperNode.nameGet(NAME, "getNameInformed");
		assertEquals("getNameInformed", field.nameForGet());
	}

	@Test
	public void testNameForSet() throws Exception {
		assertEquals("setName", field.nameForSet());
	}

	@Test
	public void testNameForSetInformad() throws Exception {
		field = MapperNode.nameSet(NAME, "setNameInformed");
		assertEquals("setNameInformed", field.nameForSet());
	}

	@Test
	public void testIsFieldOnly() throws Exception {
		field = MapperNode.onField(NAME);
		assertTrue(field.isFieldOnly());
	}

	@Test
	public void testIsFieldOnlyFalse() throws Exception {
		assertFalse(field.isFieldOnly());
	}

}
