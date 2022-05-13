package br.com.elementi.core.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import org.eclipse.persistence.oxm.annotations.XmlPath;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType
public class XmlAddress {

	@XmlElement(name = "ModScpIndctn")
	protected String operation;

	@XmlPath("./")
	protected XmlAddressValue value = new XmlAddressValue();

	@XmlPath("Adr/")
	protected XmlAddressValue value_Adr;

	public XmlAddressValue value() {
		return operation == null ? value : value_Adr;
	}

}
