package br.com.elementi.core.qtable;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import br.com.elementi.core.Unit;
import br.com.elementi.core.qtable.QOptional;
import br.com.elementi.core.tools.Reflect;
import br.com.elementi.test.DummyPerson;

public class QOptionalTest extends Unit {

	private QOptional optional;
	private DummyPerson entity;

	@Before
	public void before() throws Exception {
		entity = new DummyPerson();
		Reflect.setValueTo(entity, "name", "Test QOptional");
		optional = QOptional.of(DummyPerson.class, entity);
	}

	@Test
	public void testFound() throws Exception {
		assertTrue(optional.isFound());
	}

	@Test
	public void testFoundFalse() throws Exception {
		assertFalse(QOptional.of(DummyPerson.class, null).isFound());
	}

	@Test
	public void testGet() throws Exception {
		DummyPerson get = optional.get();
		assertEquals(entity.getName(), get.getName());
	}

	@Test
	public void testGetNotFound() throws Exception {
		DummyPerson get = QOptional.of(DummyPerson.class, null).get();
		assertFalse(entity.getName().equals(get.getName()));
	}

}
