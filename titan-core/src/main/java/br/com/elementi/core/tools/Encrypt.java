package br.com.elementi.core.tools;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class Encrypt {

	private static SecretKey key = generateKey();

	private static SecretKey generateKey() {
		try {
			return KeyGenerator.getInstance("DES").generateKey();
		} catch (Exception e) {
			throw new IllegalArgumentException("Encript Exception");
		}
	}

	public static String doEncode(String toEnconde) throws Exception {
		Cipher cipher = Cipher.getInstance("DES");
		cipher.init(Cipher.ENCRYPT_MODE, key);
		return new BASE64Encoder().encode(cipher.doFinal(toEnconde.getBytes("UTF8")));
	}

	public static String doDecode(String encode) throws Exception {
		Cipher cipher = Cipher.getInstance("DES");
		cipher.init(Cipher.DECRYPT_MODE, key);
		return new String(cipher.doFinal(new BASE64Decoder().decodeBuffer(encode)), "UTF8");
	}

}
