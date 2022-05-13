package br.com.elementi.core.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType
public class XmlBodyDetail {

	@XmlElement(name = ".")
	public XmlBodyAttribute attr = new XmlBodyAttribute();

	@XmlElement(name = "AcctDtlsConf")
	private XmlDetailAccount detailAccount;

	@XmlElement(name = "AcctLkDtls")
	private XmlDetailLink detailDetail = new XmlDetailLink();

}
