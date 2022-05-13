package br.com.elementi.core.tools;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import org.hamcrest.core.IsNot;
import org.junit.Test;

import br.com.elementi.core.tools.Extract;

public class ExtractTest {

	@Test
	public void testEarPath() throws Exception {
		String value = "C:/icadx-business-ear.ear/icadx-service-metadata-0.0.1.jar/";
		String extracted = Extract.earPath(value);
		assertEquals("C:/icadx-business-ear.ear", extracted);
	}

	@Test
	public void testEarPathFail() throws Exception {
		String value = "C:/icadx-business-ear.r/";
		String extracted = Extract.earPath(value);
		assertThat(extracted, IsNot.not("C:/icadx-business-ear.ear"));
	}

	@Test
	public void testJarName() throws Exception {
		String value = "C:/icadx-business-ear.ear/icadx-service-metadata-0.0.1.jar/";
		String extracted = Extract.jarName(value);
		assertEquals("icadx-service-metadata-0.0.1.jar/", extracted);
	}

	@Test
	public void testJarNameFail() throws Exception {
		String value = "C:/icadx-business-ear.ea1r/icadx-service-metadata-0.0.1.jar/";
		String extracted = Extract.jarName(value);
		assertThat(extracted, IsNot.not("icadx-service-metadata-0.0.1.jar/"));
	}

	@Test
	public void testProjectName() throws Exception {
		String value = "C:/something/somewere/titan-core/META-INF/domain/domain.xml";
		String projectName = Extract.projectName(value);
		assertEquals("titan-core", projectName);
	}

}
