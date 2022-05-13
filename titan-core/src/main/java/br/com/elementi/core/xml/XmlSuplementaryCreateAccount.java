package br.com.elementi.core.xml;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType
public class XmlSuplementaryCreateAccount {

	@XmlElement(name = "InvstmtAcctXtnsn")
	private XmlSuplementaryAccount suplementaryAccount;

	@XmlElement(name = "OrgXtnsn")
	private XmlSuplementaryOrganization suplementaryOrganization;

	@XmlElement(name = "IndvPrsnXtnsn")
	private XmlSuplementaryIndividual suplementaryIndividual;

	@XmlElement(name = "SpcfcCtrctAcct")
	private List<XmlSpecificContract> specificContracts;

}
