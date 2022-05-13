package br.com.elementi.core.xml;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType
public class XmlSuplementaryAccount {

	@XmlElement(name = "SctyAcctTp")
	protected String accountType;

	@XmlElement(name = "CshAcctId")
	protected List<XmlCash> cashs;

	@XmlElement(name = "SctiesMvmntRule")
	protected String lifoFifo;

	@XmlElement(name = "AcctAdrTp")
	protected String addressType;

	@XmlElement(name = "TpOfCollstn")
	protected String collateralization;

	@XmlElement(name = "OnBhlfAcctInd")
	protected String finalAccountIndication;

}
