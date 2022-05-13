package br.com.elementi.core.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

import org.eclipse.persistence.oxm.annotations.XmlPath;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType
public class XmlLink {

	@XmlPath("PrvsRef/RefIssr/PrtryId/Id/text()")
	private String categoryParticipant;

	@XmlPath("LkdAcctInf/LkdAcctTp/text()")
	private String linkType;

	@XmlPath("LkdAcctInf/LkdAcctSts/text()")
	private String linkSituation;

	@XmlPath("LkdAcctInf/LkdAcctStsDt/text()")
	private String linkSituationDate;

	@XmlPath("LkdAcctInf/LkdAcctMod/ModScpIndctn/text()")
	private String operation;

	@XmlPath("LkdAcctInf/LkdAcctMod/LkdAcctDtls/CntrPtyInf")
	private XmlLinkAccount counterPart;

	@XmlPath("LkdAcctInf/LkdAcctMod/LkdAcctDtls/PtyInf")
	private XmlLinkAccount part;


}
