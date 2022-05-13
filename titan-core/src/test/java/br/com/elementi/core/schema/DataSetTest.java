package br.com.elementi.core.schema;

import org.junit.Test;

import br.com.elementi.core.Unit;

public class DataSetTest extends Unit {

	@Test
	public void testCreate() throws Exception {
		DataSet dataSet = DataSet.create("Test");
	}

}
