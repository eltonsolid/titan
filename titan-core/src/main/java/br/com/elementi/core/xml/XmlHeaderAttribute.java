package br.com.elementi.core.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType
public class XmlHeaderAttribute {

	@XmlAttribute(name = "xmlns:xsi")
	private String xsi = "http://www.w3.org/2001/XMLSchema-instance";

	@XmlAttribute(name = "xsi:schemaLocation")
	private String schemaLocation = "urn:iso:std:iso:20022:tech:xsd:head.001.001.01 head.001.001.01.xsd";

	@XmlAttribute(name = "xmlns")
	private String xmlns = "urn:iso:std:iso:20022:tech:xsd:head.001.001.01";

}
