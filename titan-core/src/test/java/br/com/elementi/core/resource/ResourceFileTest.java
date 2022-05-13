package br.com.elementi.core.resource;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.File;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import br.com.elementi.core.Unit;
import br.com.elementi.core.resource.ResourceFile;

@Deprecated
@Ignore
public class ResourceFileTest extends Unit {

	private ResourceFile resourceFile;

	@Before
	public void before() throws Exception {
		String path = ClassLoader.getSystemResource("container").getPath();
		resourceFile = ResourceFile.create(new File(path));
	}

	@Test
	public void testcreate() throws Exception {
		assertNotNull(resourceFile);
	}

	@Test
	public void testSize() throws Exception {
		assertEquals(TWO, resourceFile.size());
	}

	@Test
	public void testSizeOne() throws Exception {
		String path = ClassLoader.getSystemResource("META-INF/domain").getPath();
		System.err.println(path);
		resourceFile = ResourceFile.create(new File(path));
		assertEquals(ONE, resourceFile.size());
	}

	@Test
	public void testFilter() throws Exception {
		String path = ClassLoader.getSystemResource("br/com/elementi/test").getPath();
		ResourceFile filter = ResourceFile.create(path).filter(null);
		Integer four = 4;
		assertEquals(four, filter.size());
	}

}
