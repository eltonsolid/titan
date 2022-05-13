package br.com.elementi.core.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType
public class XmlSuplementaryData {

	@XmlElement(name = ".")
	protected XmlSuplementaryDataAttribute attr = new XmlSuplementaryDataAttribute();

	@XmlElement(name = "AcctOpngInstrSD")
	protected XmlSuplementaryCreateAccount suplementaryCreateAccount;

	@XmlElement(name = "AcctModInstrSD")
	protected XmlSuplementaryUpdateAccount suplementaryUpdateAccount;

	@XmlElement(name = "AcctDtlsConfSD")
	protected XmlSuplementaryDetailAccount detail;



}
