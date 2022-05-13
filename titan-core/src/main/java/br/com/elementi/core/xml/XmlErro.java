package br.com.elementi.core.xml;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import org.eclipse.persistence.oxm.annotations.XmlPath;
import org.joda.time.DateTime;
import org.joda.time.format.ISODateTimeFormat;

import com.google.common.collect.Lists;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType
public class XmlErro {

	@XmlPath("RptId/Id/text()")
	protected String id;

	@XmlPath("RptId/CreDtTm/text()")
	protected String date;

	@XmlPath("NbOfErrs/Nb/text()")
	protected String total;

	@XmlElement(name = "ErrDesc")
	protected List<XmlErroDetail> erroDetails;

	public static XmlErro create(XmlHeader sender) {
		XmlErro erro = new XmlErro();
		erro.id = sender.creator.messageIdentification;
		erro.date = ISODateTimeFormat.dateTimeNoMillis().print(DateTime.now());
		erro.total = "0";
		erro.erroDetails = Lists.newArrayList();
		return erro;
	}

	public void add(String code, String description) {
		XmlErroDetail erroDetail = new XmlErroDetail();
		erroDetail.sequence = erroDetails.size() + "";
		erroDetail.code = code;
		erroDetail.description = description;
		erroDetails.add(erroDetail);
		total = erroDetails.size() + "";
	}

}
