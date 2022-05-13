package br.com.elementi.core.soap;

import java.util.Properties;

import org.joda.time.DateTime;

import br.com.elementi.core.tools.Encrypt;

public class SoapStoragePassword {

	private Properties properties;

	public SoapStoragePassword(Properties properties) {
		this.properties = properties;
	}

	public static SoapStoragePassword load(Properties properties) {
		return new SoapStoragePassword(properties);
	}

	public String createPasswordForIdentify(String identify) throws Exception {
		if (properties.containsKey(identify)) {
			return createPasswordFor(properties.get(identify).toString());
		}
		return "";
	}

	private String createPasswordFor(String value) throws Exception {
		String password = value + "@" + String.valueOf(DateTime.now().getMinuteOfDay());
		String encode = Encrypt.doEncode(password);
		return encode;
	}

}
