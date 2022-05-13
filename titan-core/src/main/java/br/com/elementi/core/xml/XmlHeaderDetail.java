package br.com.elementi.core.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType
public class XmlHeaderDetail {

	@XmlElement(name = ".")
	public XmlHeaderAttribute attr = new XmlHeaderAttribute();

	@XmlElement(name = ".")
	private XmlHeaderValue creator = new XmlHeaderValue();

	@XmlElement(name = "Rltd")
	private XmlHeaderValue destination = new XmlHeaderValue();

}
