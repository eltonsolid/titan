package br.com.elementi.core.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

import org.eclipse.persistence.oxm.annotations.XmlPath;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType
public class XmlFromTo {

	@XmlPath("OrgId/Id/OrgId/Othr/Id/text()")
	protected String identification = "-";

	@XmlPath("OrgId/Id/OrgId/Othr/SchmeNm/Prtry/text()")
	protected String defaultSchemaName = "39";

	@XmlPath("OrgId/Id/OrgId/Othr/Issr/text()")
	protected String defaultIssuer = "40";

}
