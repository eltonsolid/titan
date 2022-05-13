package br.com.elementi.core.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType
public class XmlDocument {

	@XmlElement(name = "XtndedIdTp")
	protected String type;

	@XmlElement(name = "Id")
	protected String number;

	@XmlElement(name = "Ctry")
	protected String country;

	@XmlElement(name = "IsseDt")
	protected String registerDate;

	@XmlElement(name = "XpryDt")
	protected String validateDate;

	@XmlElement(name = "DtDprtureCtry")
	protected String departureDate;

}
