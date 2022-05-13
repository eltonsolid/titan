package br.com.elementi.core.tools;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import br.com.elementi.core.Unit;
import br.com.elementi.core.exception.NullConstructionException;
import br.com.elementi.test.DummyEntity;
import br.com.elementi.test.DummyEntityBroken;
import br.com.elementi.test.DummyEntityType;
import br.com.elementi.test.DummyEntityWithClassParamter;

public class InitTest extends Unit {

	@Test
	public void testInitializer() throws Exception {
		DummyEntity entity = Init.initializer(DummyEntity.class);
		assertEquals(Integer.MIN_VALUE, entity.getIdade());
	}

	@Test
	public void testType() throws Exception {
		DummyEntity entity = Init.initializer(DummyEntity.class);
		assertEquals(DummyEntityType.UNDEFINE, entity.getType());
	}

	@Test
	public void testList() throws Exception {
		DummyEntity entity = Init.initializer(DummyEntity.class);
		assertTrue(entity.getCodes().isEmpty());
	}

	@Test
	public void testSet() throws Exception {
		DummyEntity entity = Init.initializer(DummyEntity.class);
		assertTrue(entity.getKeys().isEmpty());
	}

	@Test
	public void testMap() throws Exception {
		DummyEntity entity = Init.initializer(DummyEntity.class);
		assertTrue(entity.getCombos().isEmpty());
	}


	@Test(expected = NullConstructionException.class)
	public void testInitializerFail() throws Exception {
		Init.initializer(DummyEntityBroken.class);
	}

	@Test
	public void testInitializerWithClassParamter() throws Exception {
		DummyEntityWithClassParamter entity = Init.initializer(DummyEntityWithClassParamter.class);
		assertNotNull(entity.getClasse());
	}

}
