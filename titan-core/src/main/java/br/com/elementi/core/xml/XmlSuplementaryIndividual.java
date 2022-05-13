package br.com.elementi.core.xml;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlType;

import com.google.common.collect.Lists;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType
public class XmlSuplementaryIndividual {

	@XmlElement(name = "NonResdtRegdRgltr")
	protected String cvmRegister ;

	@XmlElement(name = "MrtlSts")
	protected String civelStatus;

	@XmlElement(name = "FthrNm")
	protected String fatherName;

	@XmlElement(name = "MthrNm")
	protected String matherName;

	@XmlElement(name = "PrnrNm")
	protected String partnerName;

	@XmlElement(name = "EdctLvl")
	protected String education;

	@XmlElement(name = "AnulWlthVal")
	protected XmlMoney annualWealth;

	@XmlElement(name = "AnulWlthValRefDt")
	protected String annualWealthDate;

	@XmlElement(name = "EqtyVal")
	protected XmlMoney amountOfAssets;

	@XmlElement(name = "EqtyValRefDt")
	protected String amountOfAssetsDate;

	@XmlElement(name = "PltlExpdPrsn")
	protected String exposedPoliticallyPerson;

	@XmlElement(name = "LkdPrsnInd")
	protected String personLinkedToParticipant;

	@XmlElement(name = "ResdtlCtry")
	protected String residenceCountry;

	@XmlElement(name = "AltrntvOthrId")
	protected List<XmlDocument> documents;

	@XmlElements(value = { @XmlElement(name = "OthrAdr"), @XmlElement(name = "ModfdOthrAdr") })
	protected List<XmlAddress> addresses;

	@XmlElement(name = "OthrComAdr")
	protected List<XmlContact> contacts;

}
