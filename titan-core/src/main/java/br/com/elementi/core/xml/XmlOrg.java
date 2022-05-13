package br.com.elementi.core.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import org.eclipse.persistence.oxm.annotations.XmlPath;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType
public class XmlOrg {

	@XmlElement(name = "Nm")
	protected  String name;

	@XmlElement(name = "RegnDt")
	protected  String registerDate;

	@XmlElement(name = "Purp")
	protected  String purpose;

	@XmlElement(name = "Id/PrtryId/Id/text()")
	protected  String mainDocument;

	@XmlElement(name = "Id/PrtryId/SchmeNm/text()")
	protected  String mainDocumentType;

	@XmlPath("PstlAdr")
	protected  XmlAddressMain address;

	@XmlPath("PmryComAdr/Phne/text()")
	protected  String phone;

	@XmlPath("PmryComAdr/Email/text()")
	protected  String email;

	@XmlPath("ScndryComAdr/Phne/text()")
	protected  String secundaryPhone;

	@XmlPath("ScndryComAdr/Email/text()")
	protected  String secundaryEmail;

}
