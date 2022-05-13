package br.com.elementi.core.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType
public class XmlAddressValue {

	@XmlElement(name = "AdrTp")
	private String type;

	@XmlElement(name = "StrtNm")
	private String street;

	@XmlElement(name = "AdrLine")
	private String complement;

	@XmlElement(name = "BldgNb")
	private String number;

	@XmlElement(name = "PstCd")
	private String postalCode;

	@XmlElement(name = "TwnNm")
	private String city;

	@XmlElement(name = "CtrySubDvsn")
	private String state;

	@XmlElement(name = "Ctry")
	private String country;

}
