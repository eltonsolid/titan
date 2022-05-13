package br.com.elementi.core.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

import org.eclipse.persistence.oxm.annotations.XmlPath;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType
public class XmlLinkAccount {

	@XmlPath("PtyId/PrtryId/Id/text()")
	private String categoryParticipant;

	@XmlPath("PtyId/PrtryId/Issr/text()")
	private String defaultIssuer = "40";

	@XmlPath("PtyId/PrtryId/SchmeNm/text()")
	private String defaultSchemaName = "39";

	@XmlPath("SctyAcctTp/text()")
	private String type;

	@XmlPath("AcctId/Prtry/Id/text()")
	private String account;

}
