package br.com.elementi.core.xml;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import com.google.common.collect.Lists;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType
public class XmlSuplementaryOrganization {

	@XmlElement(name = "ResdtlSts")
	protected String residentSituation;

	@XmlElement(name = "NonResdtRegdRgltr")
	protected String cvmRegister;

	@XmlElement(name = "LglStre")
	protected String constituationForm;

	@XmlElement(name = "MktCptlstn")
	protected XmlMoney marketCaptalByCVM;

	@XmlElement(name = "MktCptlstnRefDt")
	protected String marketCaptalByCVMDate;

	@XmlElement(name = "NetEqty")
	protected XmlMoney companyBalance;

	@XmlElement(name = "NetEqtyRefDt")
	protected String companyBalanceDate;

	@XmlElement(name = "WrkgCptl")
	protected XmlMoney workCaptal;

	@XmlElement(name = "WrkgCptlRedDt")
	protected String workCaptalDate;

	@XmlElement(name = "LkdPrsnInd")
	protected String personLinkedToParticipant;

	@XmlElement(name = "RegnCtry")
	protected String countryRegistration;

	@XmlElement(name = "OthrId")
	protected List<XmlDocument> documents;

	@XmlElement(name = "InvstrTp")
	protected String noResidentInvestidorType;

	@XmlElement(name = "CpnyBrShrs")
	protected String formaSociedade;

	@XmlElement(name = "StsNIF")
	protected String nifIndication;

	@XmlElement(name = "InstrCVM286")
	protected String cvm286;
}
