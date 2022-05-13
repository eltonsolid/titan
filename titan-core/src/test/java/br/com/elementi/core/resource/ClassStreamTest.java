package br.com.elementi.core.resource;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import javax.persistence.Entity;

import org.junit.Before;
import org.junit.Test;

public class ClassStreamTest {

	String path = "C:/development/workspace/titan-core/target/test-classes/br/com/elementi/test/DummyEntity.class";
	private ClassStream classStream;

	@Before
	public void before() throws Exception {
		path = new File("").getAbsolutePath() + "/target/test-classes/br/com/elementi/test/DummyEntity.class";
		classStream = ClassStream.create(new ResourceStream("DummyEntity", Files.newInputStream(Paths.get(path))));
	}

	@Test
	public void testClassName() throws Exception {
		String name = classStream.name();
		assertEquals("br.com.elementi.test.DummyEntity", name);
	}

	@Test
	public void testAnnotation() throws Exception {
		List<String> annotations = classStream.annotation();
		String annotation = annotations.get(0);
		assertEquals(Entity.class.getName(), annotation);
	}

}
