package br.com.elementi.core.xml;

import javax.xml.bind.annotation.XmlElement;

import org.eclipse.persistence.oxm.annotations.XmlPath;

public class XmlTreasuryContract {

	@XmlElement(name = "AcsnDt")
	protected String accessionDate;

	@XmlElement(name = "TrsrTradr")
	protected String operationOwner;

	@XmlElement(name = "SpcfcCtrctTpSts")
	protected String situation;

	@XmlPath("TrsrTax/Rate/text()")
	protected String taxa;

}
