package br.com.elementi.core.tools;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.google.common.collect.Lists;

import br.com.elementi.core.Unit;
import br.com.elementi.core.exception.NotExpectedException;
import br.com.elementi.core.qtable.QTable;
import br.com.elementi.test.DummyEntity;
import br.com.elementi.test.DummyEntityType;

public class BuilderEntityTest extends Unit {

	private DummyEntity.Builder builderEntity;
	private DummyEntity.Builder builderTable;
	private QTable table;

	@Before
	public void before() throws Exception {
		DummyEntity entity = Init.initializer(DummyEntity.class);
		table = Mockito.mock(QTable.class);
		Mockito.when(table.find()).thenReturn(entity);
		Mockito.when(table.list()).thenReturn(Lists.newArrayList(entity));
		builderEntity = BuilderEntity.entity(DummyEntity.Builder.class);
		builderTable = BuilderEntity.table(table, DummyEntity.Builder.class);
	}

	@Test
	public void testGet() throws Exception {
		DummyEntity entity = builderEntity.get();
		assertNotNull(entity);
	}

	@Test
	public void testList() throws Exception {
		List<DummyEntity> entity = builderEntity.list();
		assertFalse(entity.isEmpty());
	}

	@Test(expected = NotExpectedException.class)
	public void testNotExpected() throws Exception {
		builderEntity.document("");
	}

	@Test
	public void testEntityGetDefaultValue() throws Exception {
		DummyEntity entity = builderEntity.get();
		assertTrue(entity.id.getValue().equals(Integer.MIN_VALUE));
	}

	@Test
	public void testEntityGetDefaultEnum() throws Exception {
		DummyEntity entity = builderEntity.get();
		assertEquals(entity.getType(), (DummyEntityType.UNDEFINE));
	}

	@Test
	public void testEntitySetOneValue() throws Exception {
		DummyEntity entity = builderEntity.name("Eltonsolid").get();
		assertEquals("Eltonsolid", entity.getName());
	}

	@Test
	public void testQTableGet() throws Exception {
		DummyEntity entity = builderTable.get();
		Integer expected = Integer.MIN_VALUE;
		assertEquals(expected, entity.id.getValue());
	}

	@Test
	public void testQTableList() throws Exception {
		List<DummyEntity> entities = builderTable.list();
		Integer expected = Integer.MIN_VALUE;
		assertEquals(expected, entities.get(0).id.getValue());
	}

	@Test
	public void testQTableAddConstraint() throws Exception {
		builderTable.name("Eltonsolid");
		Mockito.verify(table).eq(Mockito.matches("name"), Mockito.matches("Eltonsolid"));
	}

	@Test
	public void testQTableAddConstraintFail() throws Exception {
		builderTable.name("Eltonsolid");
		//Mockito.verify(table).eq(Mockito.matches("name"), Mockito.anyInt());
	}

	@Test
	public void testQTableAddConstraintSecondValue() throws Exception {
		builderTable.name("Eltonsolid").idade(1);
		Mockito.verify(table).eq(Mockito.matches("idade"), Mockito.anyInt());
	}



}
