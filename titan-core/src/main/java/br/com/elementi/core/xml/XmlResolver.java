package br.com.elementi.core.xml;

import org.joda.time.DateTime;

import com.google.common.base.Strings;

public class XmlResolver {

	public enum XmlFileOperation {
		UNDEFINE, ACCOUNT_INSERT, ACCOUNT_UPDATE, ACCOUNT_DETAIL, ACCOUNT_HASH, LINK_INSERT_UPDATE, LINK_DETAIL
	}

	public static final String ACCOUNT_TYPE = "BVBG.001.03";
	public static final String ACCOUNT_RESPONSE_TYPE = "BVBG.002.03";

	public static final String ACCOUNT_DEFINATION = "BVMF.002.03";

	public static final String LINK_TYPE = "BVBG.005.01";
	public static final String LINK_RESPONSE = "BVBG.005.01";
	public static final String LINK_DEFINATION = "BVMF.006.01";

	public static final String HASH_TYPE = "BVBG.082.01";
	public static final String HASH_RESPONSE = "BVBG.083.01";
	public static final String HASH_DEFINATION = "BVMF.189.03";

	public static final String ERRO_RESPONSE = "BVBG.000.00";
	public static final String ERRO_DEFINATION = "TSMT.016.001.03";

	public static String bizzGroupId(String identification) {
		String participant = Strings.padStart(identification.replaceFirst("\\d-", ""), 5, '0');
		String time = DateTime.now().toString("HHmmSS");
		String hash = time.hashCode() + "";
		String code = hash + participant;
		if (code.length() > 12) {
			code = code.substring(code.length() - 11);
		}
		return "BV000336" + DateTime.now().toString("yyyyMMdd") + "0001" + code + time;
	}

	public static XmlFileOperation operation(XmlFileHeader creator) {
		String value = creator.fileType;
		if (value.matches("BVBG\\.001.*")) {
			return XmlFileOperation.ACCOUNT_INSERT;
		}
		if (value.matches("BVBG\\.003.*")) {
			return XmlFileOperation.ACCOUNT_UPDATE;
		}
		if (value.matches("BVBG\\.009.*")) {
			return XmlFileOperation.ACCOUNT_DETAIL;
		}
		if (value.matches("BVBG\\.082.*")) {
			return XmlFileOperation.ACCOUNT_HASH;
		}
		if (value.matches("BVBG\\.004.*")) {
			return XmlFileOperation.LINK_INSERT_UPDATE;
		}
		if (value.matches("BVBG\\.010.*")) {
			return XmlFileOperation.LINK_DETAIL;
		}
		return XmlFileOperation.UNDEFINE;
	}

}
