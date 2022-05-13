package br.com.elementi.core.soap;

import static org.junit.Assert.*;

import java.util.Properties;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

@Ignore
public class SoapStoragePasswordTest {

	private static final String IDENTIFY = "ws-soap";
	private SoapStoragePassword storagePassword;

	@Before
	public void before() throws Exception {
		Properties properties = new Properties();
		properties.put(IDENTIFY, "127.0.0.1");
		storagePassword = SoapStoragePassword.load(properties);
	}

	@Test
	public void testCreatePasswordForIdentify() throws Exception {
		String password = storagePassword.createPasswordForIdentify(IDENTIFY);
		assertNotNull(password);
	}

	@Test
	public void shouldBeIqual() throws Exception {
		String first = storagePassword.createPasswordForIdentify(IDENTIFY);
		String second = storagePassword.createPasswordForIdentify(IDENTIFY);
		assertEquals(second, first);
	}

}
