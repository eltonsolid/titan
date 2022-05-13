package br.com.elementi.core.xml;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import org.eclipse.persistence.oxm.annotations.XmlPath;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType
public class XmlDetailHash {

	@XmlPath("PtyId/PrtryId/Id/text()")
	private String categoryParticipant;

	@XmlPath("PtyId/PrtryId/Issr/text()")
	private String defaultIssuer;

	@XmlPath("PtyId/PrtryId/SchmeNm/text()")
	private String defaultSchemaName;

	@XmlElement(name = "AcctInf")
	private List<XmlHashAccount> hashAccounts;

}
