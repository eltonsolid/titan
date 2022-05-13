package br.com.elementi.core.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType
public class XmlBodyAttribute {

	@XmlAttribute(name = "xmlns:xsi")
	private String xsi = "http://www.w3.org/2001/XMLSchema-instance";

	@XmlAttribute(name = "xsi:schemaLocation")
	private String schemaLocation;

	@XmlAttribute(name = "xmlns")
	private String xmlns;

	@XmlAttribute(name = "xmlns:supl")
	private String supl;

	public static XmlBodyAttribute detailAccount() {
		XmlBodyAttribute attribute = new XmlBodyAttribute();
		attribute.schemaLocation = "urn:bvmf.002.03.xsd bvmf.002.03.xsd";
		attribute.xmlns = "urn:bvmf.002.03.xsd";
		attribute.supl = "urn:SUPL.002.03.xsd";
		return attribute;
	}

	public static XmlBodyAttribute detailLink() {
		XmlBodyAttribute attribute = new XmlBodyAttribute();
		attribute.schemaLocation = "urn:bvmf.006.01.xsd bvmf.006.01.xsd";
		attribute.xmlns = "urn:bvmf.006.01.xsd";
		return attribute;
	}

	public static XmlBodyAttribute detailHash() {
		XmlBodyAttribute attribute = new XmlBodyAttribute();
		attribute.schemaLocation = "urn:bvmf.083.01.xsd bvmf.083.01.xsd";
		attribute.xmlns = "urn:bvmf.083.01.xsd";
		attribute.supl = "urn:SUPL.083.01.xsd";
		return attribute;
	}

	public static XmlBodyAttribute erro() {
		XmlBodyAttribute attribute = new XmlBodyAttribute();
		attribute.schemaLocation = "urn:iso:std:iso:20022:tech:xsd:tmst.016.001.03 tmst.016.001.03.xsd";
		attribute.xmlns = "urn:iso:std:iso:20022:tech:xsd:tmst.016.001.03";
		return attribute;
	}

}
