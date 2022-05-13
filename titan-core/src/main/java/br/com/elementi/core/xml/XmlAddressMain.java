package br.com.elementi.core.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import org.eclipse.persistence.oxm.annotations.XmlPath;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType
public class XmlAddressMain {

	@XmlElement(name = "AdrTp")
	protected String mainType;

	@XmlElement(name = "MlngInd")
	protected String main;

	@XmlPath("NmAndAdr/Adr")
	protected XmlAddress address;

}
