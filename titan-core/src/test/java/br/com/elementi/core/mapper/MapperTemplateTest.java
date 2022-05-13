package br.com.elementi.core.mapper;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import br.com.elementi.core.Unit;
import br.com.elementi.test.DummyPerson;
import br.com.elementi.test.DummyPersonContract;

public class MapperTemplateTest extends Unit {

	private MapperTemplate template;

	@Before
	public void before() throws Exception {
		template = MapperTemplate.with(DummyPerson.class, DummyPersonContract.class);
	}

	@Test
	public void testMapperFields() throws Exception {
		assertEquals(ZERO, template.size());
	}

	@Test
	public void testAdd() throws Exception {
		template.add("name", "name");
		assertEquals(ONE, template.size());
	}

	@Test
	public void testAddSame() throws Exception {
		template.add("name", "name").add("name", "name");
		assertEquals(ONE, template.size());
	}

	@Test
	public void testAddAnother() throws Exception {
		template.add("name", "name").add("name", "age");
		assertEquals(TWO, template.size());
	}


	@Test
	public void testAddWithTwoParamterMapper() throws Exception {
		template.add(MapperField.onGet("name"), MapperField.onGet("name"));
		assertEquals(ONE, template.size());
	}

}
