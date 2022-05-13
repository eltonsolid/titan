package br.com.elementi.core.tools;

import static org.junit.Assert.*;

import org.junit.Test;

public class EncryptTest {

	@Test
	public void testEncode() throws Exception {
		String encode = Encrypt.doEncode("Eltonsolid");
		assertNotNull(encode);
	}

	@Test
	public void testDecode() throws Exception {
		String encode = Encrypt.doEncode("Eltonsolid");
		String doDecode = Encrypt.doDecode(encode);
		assertEquals("Eltonsolid", doDecode);
	}

}
