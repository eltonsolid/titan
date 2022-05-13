package br.com.elementi.core.xml;

import javax.xml.bind.annotation.XmlElement;

public class XmlContact {

	@XmlElement(name = "EmailTp")
	private String emailType = "0";

	@XmlElement(name = "Email")
	private String email = "0";

	@XmlElement(name = "PhneTp")
	private String phoneType = "0";

	@XmlElement(name = "Phne")
	private String phone = "0";

}
