package br.com.elementi.core.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

import org.eclipse.persistence.oxm.annotations.XmlPath;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType
public class XmlAccount {

	@XmlPath("AcctSvcr/PrtryId/Id/text()")
	protected String categoryParticipant;

	@XmlPath("Id/Prtry/Id/text()")
	protected String number;

}
