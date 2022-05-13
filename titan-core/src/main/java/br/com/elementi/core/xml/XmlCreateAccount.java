package br.com.elementi.core.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import org.eclipse.persistence.oxm.annotations.XmlPath;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType
public class XmlCreateAccount {

	@XmlElement(name = "InvstmtAcct")
	protected XmlAccount account;

	@XmlPath("AcctPties/PmryOwnr")
	protected XmlAccountOwner accountOwner;

	@XmlPath("SplmtryData/Envlp/Cnts/Document")
	protected XmlSuplementaryData suplementaryData;

}
