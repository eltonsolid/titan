package br.com.elementi.core.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

import org.eclipse.persistence.oxm.annotations.XmlPath;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType
public class XmlHash {

	@XmlPath("PtyId/PrtryId/Id/text()")
	protected String categoryParticipant;

	@XmlPath("PtyId/PrtryId/Issr/text()")
	protected String defaultIssuer;

	@XmlPath("PtyId/PrtryId/SchmeNm/text()")
	protected String defaultSchemaName;

	@XmlPath("MvmnStartDt/Dt/text()")
	protected String startDate;

	@XmlPath("MvmnEndDt/Dt/text()")
	protected String endDate;

	@XmlPath("InitAcct/Prtry/Id/text()")
	protected String startAccount;

	@XmlPath("FnlAcct/Prtry/Id/text()")
	protected String endAccount;

}
