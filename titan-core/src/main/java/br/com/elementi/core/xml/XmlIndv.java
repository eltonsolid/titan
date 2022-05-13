package br.com.elementi.core.xml;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import org.eclipse.persistence.oxm.annotations.XmlPath;

import com.google.common.collect.Lists;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType
public class XmlIndv {

	@XmlElement(name = "Nm")
	protected String name;

	@XmlElement(name = "Gndr")
	protected String gender;

	@XmlElement(name = "BirthDt")
	protected String birthDate;

	@XmlElement(name = "CityOfBirth")
	protected String birthCity;

	@XmlElement(name = "PrvcOfBirth")
	protected String birthState;

	@XmlElement(name = "CtryOfBirth")
	protected String birthCountry;

	@XmlElement(name = "EmplngCpny")
	protected String employer;

	@XmlElement(name = "BizFctn")
	protected String occupationDescription;

	@XmlElement(name = "Prfssn")
	protected String occupation;

	@XmlPath("PmryComAdr/Phne/text()")
	protected String phone;

	@XmlPath("PmryComAdr/Email/text()")
	protected String email;

	@XmlElement(name = "PstlAdr")
	protected List<XmlAddressMain> address;

	@XmlPath("Ctznsh/Ntlty/text()")
	protected String nationality;

	@XmlElement(name = "OthrId")
	protected XmlDocument document;

	@XmlPath("CtryAndResdtlSts/ResdtlSts/text()")
	protected String residentInBrasil;

}
