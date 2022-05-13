package br.com.elementi.core.schema;

import static org.junit.Assert.assertEquals;

import java.util.HashSet;

import org.junit.Before;
import org.junit.Test;

import br.com.elementi.core.Unit;
import br.com.elementi.core.schema.Row;

import com.google.common.collect.Sets;

public class RowTest extends Unit {

	private Row row;

	@Before
	public void before() throws Exception {
		row = new Row() {

			@Override
			public String insert() {
				return "INSERT INTO TABLE VALUES(1,'TEST',SYSDATE)";
			}

			@Override
			public String delete() {
				return "DELETE TABLE WHERE ID = ";
			}
		};
	}

	@Test
	public void testInsert() throws Exception {
		assertEquals("INSERT INTO TABLE VALUES(1,'TEST',SYSDATE)", row.insert());
	}

	@Test
	public void testDelete() throws Exception {
		assertEquals("DELETE TABLE WHERE ID = ", row.delete());
	}

	@Test
	public void testHascode() throws Exception {
		HashSet<Row> rows = Sets.newHashSet();
		rows.add(row);
		rows.add(new Row() {

			@Override
			public String insert() {
				return "INSERT INTO TABLE VALUES(1,'TEST',SYSDATE)";
			}

			@Override
			public String delete() {
				return "DELETE TABLE WHERE ID = ";
			}
		});
		assertEquals(ONE.intValue(), rows.size());
	}

}
