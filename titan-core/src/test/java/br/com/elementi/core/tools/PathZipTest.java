package br.com.elementi.core.tools;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import org.junit.Before;
import org.junit.Test;

import br.com.elementi.core.model.PathZip;

public class PathZipTest {

	private static final String EAR_PATH = ClassLoader.getSystemResource("container/test-file.ear").getPath();
	private static final String JAR_PATH = "jar-in-test-file.jar";
	private PathZip pathZip;

	@Before
	public void before() throws Exception {
		pathZip = PathZip.extractPathZip(EAR_PATH, JAR_PATH);
	}

	@Test
	public void testZipPath() throws Exception {
		assertEquals(EAR_PATH, pathZip.zipPath());
	}

	@Test
	public void testJarName() throws Exception {
		assertEquals(JAR_PATH, pathZip.jarName());
	}

	@Test
	public void testZipFile() throws Exception {
		ZipFile zipFile = pathZip.zipFile();
		assertNotNull(zipFile);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testZipFileFail() throws Exception {
		PathZip.extractPathZip("C:/titan.1ear", JAR_PATH).zipFile();
	}

	@Test
	public void testZipEntry() throws Exception {
		ZipEntry zipEntry = pathZip.zipEntry();
		assertNotNull(zipEntry);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testZipEntryFail() throws Exception {
		PathZip.extractPathZip(EAR_PATH, "XPTO").zipEntry();
	}

}
