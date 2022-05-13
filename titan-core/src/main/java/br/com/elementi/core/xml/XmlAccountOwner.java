package br.com.elementi.core.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType
public class XmlAccountOwner {

	@XmlElement(name = "Org")
	protected XmlOrg organization;

	@XmlElement(name = "IndvPrsn")
	protected XmlIndv individual;

}
