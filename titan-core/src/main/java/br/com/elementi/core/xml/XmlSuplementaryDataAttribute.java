package br.com.elementi.core.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType
public class XmlSuplementaryDataAttribute {

	@XmlAttribute(name = "xmlns:xsi")
	private String xsi = "http://www.w3.org/2001/XMLSchema-instance";

	@XmlAttribute(name = "xsi:schemaLocation")
	private String schemaLocation = "urn:SUPL.002.03.xsd SUPL.002.03.xsd";

	@XmlAttribute(name = "xmlns")
	private String xmlns = "urn:SUPL.002.03.xsd";

}
