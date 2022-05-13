package br.com.elementi.core.resource;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Ignore;
import org.junit.Test;

import br.com.elementi.core.Unit;

public class ResourcesTest extends Unit {

	@Test
	public void testAllWithContext() throws Exception {
		Resources resources = Resources.allWithContext("META-INF/domain/domain.xml");
		assertTrue(resources.size() > 0);
	}

	@Test
	public void testSearchFile() throws Exception {
		Resources resources = Resources.searchWith("br.com.elementi.core.resource.ResourcesTest.class");
		assertEquals(ONE, resources.size());
	}

	@Test
	public void testResourceDir() throws Exception {
		Resources resources = Resources.searchWith("container");
		assertEquals(TWO, resources.size());
	}

	@Test
	@Ignore
	public void testFilterProperties() throws Exception {
		Resources resources = Resources.allWithContext("META-INF/domain/domain.xml");
		int expected = 3;
		assertEquals(expected, resources.filterProperties().size());
	}

	@Test
	public void testFilterPropertiesNotFound() throws Exception {
		Resources resources = Resources.searchWith("br.com.elementi.testi");
		assertEquals(ZERO.intValue(), resources.filterProperties().size());
	}

	@Test
	public void testFilterClassFile() throws Exception {
		Resources resources = Resources.searchWith("br.com.elementi.test");
		int expected = 30;
		assertEquals(expected, resources.filterClassFiles().size());
	}

	@Test
	public void testFilterSql() throws Exception {
		Resources resources = Resources.searchWith("META-INF/script");
		assertEquals(TWO.intValue(), resources.filterSql().size());
	}

}
