package br.com.elementi.core.xml;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType
public class XmlSuplementaryUpdateAccount {

	@XmlElement(name = "ModfdInvstmtAcctXtnsn")
	protected XmlSuplementaryAccount suplementaryAccount;

	@XmlElement(name = "OrgXtnsn")
	protected XmlSuplementaryOrganization suplementaryOrganization;

	@XmlElement(name = "IndvPrsnXtnsn")
	protected XmlSuplementaryIndividual suplementaryIndividual;

	@XmlElement(name = "SpcfcCtrctAcct")
	protected List<XmlSpecificContract> specificContracts;

}
