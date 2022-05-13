package br.com.elementi.core.schema;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.Mockito;

import br.com.elementi.core.Unit;

public class SchemaTest extends Unit {

	private Database database;
	private Schema schema;

	@Before
	public void before() throws Exception {
		database = mock(Database.class);
		schema = Schema.create(database);
	}

	@Test
	public void testSize() throws Exception {
		assertEquals(ZERO, schema.size());
	}

	@Test
	public void testRow() throws Exception {
		schema.add(Row.NULL);
		assertEquals(ONE, schema.size());
	}

	@Test
	public void testStart() throws Exception {
		schema.add(Row.NULL);
		schema.clear(Row.NULL);
		schema.start();
		verify(database, times(TWO)).insertUpdate(Row.NULL.delete());
		verify(database).insertUpdate(Row.NULL.insert());
	}

	@Test
	public void testFinish() throws Exception {
		schema.add(Row.NULL);
		schema.clear(Row.NULL);
		schema.finish();
		verify(database, times(TWO)).insertUpdate(Row.NULL.delete());
	}

	@Test
	public void testInsert() throws Exception {
		schema.add(Row.NULL);
		schema.start();
		verify(database).insertUpdate(Row.NULL.insert());
	}

	@Test
	public void testCleaner() throws Exception {
		Delete one = spy(new Delete() {
			
			@Override
			public String delete() {
				return "DELETE 1";
			}
		});
		Delete two = spy(new Delete() {
			
			@Override
			public String delete() {
				return "DELETE 2";
			}
		});
		InOrder order = inOrder(one, two);
		schema.clear(one);
		schema.clear(two);
		schema.finish();
		order.verify(two).delete();
		order.verify(one).delete();
	}

	@Test
	public void testDelete() throws Exception {
		schema.add(Row.NULL);
		schema.finish();
		verify(database).insertUpdate(Row.NULL.delete());
	}

}
