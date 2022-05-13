package br.com.elementi.core.mapper;

import static org.junit.Assert.assertEquals;

import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import br.com.elementi.core.Unit;

import com.google.common.collect.Sets;

public class MapperFieldTest extends Unit {

	private static final String FIELD_ONE = "person";
	private static final String FIELD_TWO = "name";
	private static final String FIELD_DOT_FIELD = FIELD_ONE + "." + FIELD_TWO;
	private MapperField field;

	@Before
	public void before() throws Exception {
		field = MapperField.onGet(FIELD_ONE);
	}

	@Test
	public void testCreate() throws Exception {
		MapperField create = MapperField.onGet(FIELD_ONE);
		assertEquals(field.name(), create.name());
	}

	@Test
	public void testName() throws Exception {
		assertEquals(FIELD_ONE, field.name());
	}

	@Test
	public void testNameWithDot() throws Exception {
		assertEquals(FIELD_DOT_FIELD, MapperField.onGet(FIELD_DOT_FIELD).name());
	}

	@Test
	public void testSize() throws Exception {
		assertEquals(TWO, MapperField.onGet(FIELD_DOT_FIELD).size());
	}

	@Test
	public void testHash() throws Exception {
		Set<MapperField> mapperFields = Sets.newHashSet();
		mapperFields.add(MapperField.onGet(FIELD_DOT_FIELD));
		mapperFields.add(MapperField.onGet(FIELD_DOT_FIELD));
		assertEquals(ONE.intValue(), mapperFields.size());
	}

	@Test
	public void testIsForField() throws Exception {
		MapperField.onGet("field");
	}

	@Test
	public void testLastNameForGet() throws Exception {
		MapperField mapperField = MapperField.onGet(FIELD_DOT_FIELD);
		assertEquals("getName", mapperField.lastNameForGet());
	}

	@Test
	public void testLastNameForSet() throws Exception {
		MapperField mapperField = MapperField.onGet(FIELD_DOT_FIELD);
		assertEquals("setName", mapperField.lastNameForSet());
	}

}
