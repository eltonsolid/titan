package br.com.elementi.core.tools;

import org.junit.Ignore;
import org.junit.Test;

public class IOTest {

	private static final String EAR_PATH = ClassLoader.getSystemResource("container/test-file.ear").getPath();
	private static final String JAR_PATH = "jar-in-test-file.jar";

	@Test
	public void testExtractJar() throws Exception {
		//JarFile jarFile = IO.extractJar(EAR_PATH + "/" + JAR_PATH);
		//assertTrue(jarFile.entries().hasMoreElements());

	}

	@Ignore
	@Test(expected = IllegalArgumentException.class)
	public void testExtractJarFail() throws Exception {
		//IO.extractJar(EAR_PATH + "/" + JAR_PATH + "1");
	}


}
