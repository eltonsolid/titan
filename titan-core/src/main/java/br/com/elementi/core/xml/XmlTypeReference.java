package br.com.elementi.core.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

import org.eclipse.persistence.oxm.annotations.XmlPath;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType
public class XmlTypeReference {

	@XmlPath("RltdRef/Ref/text()")
	protected String referenceIdentification;

	@XmlPath("RltdRef/MsgNm/text()")
	protected String messageName;

	@XmlPath("RltdRef/MsgNm/RefIssr/PrtryId/Id/text()")
	protected String categoryParticipant;

	@XmlPath("RltdRef/MsgNm/RefIssr/PrtryId/Issr/text()")
	protected String defaultIssuer = "40";

	@XmlPath("RltdRef/MsgNm/RefIssr/PrtryId/SchmeNm/text()")
	protected String defaultSchmeName = "39";

	@XmlPath("ConfTp/ConfDtls/text()")
	protected String ConfTp;

}
