package br.com.elementi.core.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType
public class XmlSuplementaryDataDetail {

	@XmlElement(name = ".")
	public XmlSuplementaryDataAttribute attr = new XmlSuplementaryDataAttribute();

	@XmlElement(name = "AcctDtlsConfSD")
	private XmlSuplementaryDetailAccount detail = new XmlSuplementaryDetailAccount();

}
