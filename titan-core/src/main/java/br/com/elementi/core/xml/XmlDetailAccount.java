package br.com.elementi.core.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import org.eclipse.persistence.oxm.annotations.XmlPath;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType
public class XmlDetailAccount {

	@XmlElement(name = ".")
	protected XmlTypeReference reference;

	@XmlPath("InvstmtAcct/AcctSvcr/PrtryId/Id/text()")
	protected String categoryParticipant;

	@XmlPath("InvstmtAcct/AcctSvcr/PrtryId/Issr/text()")
	protected String defaultIssuer = "40";

	@XmlPath("InvstmtAcct/AcctSvcr/PrtryId/SchmeNm/text()")
	protected String defaultSchemaName = "39";

	@XmlPath("InvstmtAcct/Id/PrtryId/Id/text()")
	protected String account;

	@XmlPath("AcctPties/PmryOwnr")
	protected XmlAccountOwner accountOwner;

	@XmlPath("SplmtryData/Envlp/Cnts/Document")
	protected XmlSuplementaryData suplementaryData;

	public static XmlDetailAccount create(XmlHeader sender) {
		XmlDetailAccount detailAccount = new XmlDetailAccount();
		detailAccount.reference = new XmlTypeReference();
		detailAccount.reference.referenceIdentification = sender.creator.messageIdentification;
		detailAccount.reference.messageName = "AccountDetailsConfirmation";
		detailAccount.reference.categoryParticipant = sender.creator.from.identification;
		detailAccount.reference.ConfTp = "ACCO";
		detailAccount.categoryParticipant = sender.creator.from.identification;
		return detailAccount;
	}

}
