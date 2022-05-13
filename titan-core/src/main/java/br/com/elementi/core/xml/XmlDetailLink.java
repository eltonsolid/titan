package br.com.elementi.core.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import org.eclipse.persistence.oxm.annotations.XmlPath;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType
public class XmlDetailLink {

	@XmlElement(name = ".")
	protected XmlTypeReference reference = new XmlTypeReference();

	@XmlPath("LkdAcct/LkdAcctTp/text()")
	protected String type;

	@XmlPath("LkdAcct/LkdAcctSts/text()")
	protected String situation;

	@XmlPath("LkdAcct/LkdAcctStsDt/text()")
	protected String situationDate;

	@XmlPath("LkdAcct/CntrPtyInf")
	protected XmlLinkAccount counterPart = new XmlLinkAccount();

	@XmlPath("LkdAcct/PtyInf")
	protected XmlLinkAccount part = new XmlLinkAccount();

}
