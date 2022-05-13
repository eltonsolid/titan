package br.com.elementi.core.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import org.eclipse.persistence.oxm.annotations.XmlPath;
import org.joda.time.DateTime;
import org.joda.time.format.ISODateTimeFormat;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType
public class XmlFileHeader {

	@XmlElement(name = "Fr")
	protected XmlFromTo from;

	@XmlElement(name = "To")
	protected XmlFromTo to;

	@XmlPath("BizGrpDtls/BizGrpIdr/text()")
	protected String bizzGroupId;

	@XmlPath("BizGrpDtls/TtlNbOfMsg/text()")
	protected String bizzGroupTotal;

	@XmlPath("BizGrpDtls/BizGrpTp/text()")
	protected String fileType;

	@XmlPath("BizGrpDtls/CreDtAndTm/text()")
	protected String bizzGroupTime;

	@XmlPath("MsgTpDef/MsgDefIdr/text()")
	protected String messageDefinationType;

	@XmlPath("MsgTpDef/NbOfMsg/text()")
	protected String messageQuantity;

	public static XmlFileHeader creator(String type, String definationType, String categoryParticipant, String bizzGroupId) {
		XmlFileHeader header = create("BVMF", type, definationType, categoryParticipant, bizzGroupId);
		return header;
	}

	public static XmlFileHeader create(String identification, String type, String defination, String identificationTo, String bizzGroupId) {
		XmlFileHeader header = new XmlFileHeader();
		header.from = new XmlFromTo();
		header.from.identification = identification;
		header.to = new XmlFromTo();
		header.to.identification = identificationTo;
		header.bizzGroupId = bizzGroupId;
		header.bizzGroupTime = ISODateTimeFormat.dateTimeNoMillis().print(DateTime.now());
		header.fileType = type;
		header.bizzGroupTotal = "1";
		header.messageDefinationType = defination;
		header.messageQuantity = "1";
		return header;
	}

}
