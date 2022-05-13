package br.com.elementi.core.xml;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

import org.eclipse.persistence.oxm.annotations.XmlPath;

import com.google.common.collect.Lists;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "Document")
public class XmlFile {

	@XmlAttribute(name = "xsi:schemaLocation")
	private String schemaLocation = "urn:bvmf.052.01.xsd bvmf.052.01.xsd";

	@XmlAttribute(name = "xmlns")
	private String xmlns = "urn:bvmf.052.01.xsd";

	@XmlPath("BizFileHdr/Xchg/BizGrpDesc")
	private XmlFileHeader creator;

	@XmlPath("BizFileHdr/Xchg/Rltd")
	protected XmlFileHeader sender;

	@XmlPath("BizFileHdr/Xchg/BizGrp")
	private List<XmlBizGrp> bizGrps = Lists.newArrayList();

	public static XmlFile accountRespose(XmlFileHeader sender) {
		XmlFile response = new XmlFile();
		response.creator = XmlFileHeader.creator(XmlResolver.ACCOUNT_RESPONSE_TYPE, XmlResolver.ACCOUNT_DEFINATION, sender.from.identification, XmlResolver.bizzGroupId(sender.from.identification));
		response.sender = sender;
		return response;
	}

	public static XmlFile hash(XmlFileHeader sender) {
		XmlFile response = new XmlFile();
		response.creator = XmlFileHeader.creator(XmlResolver.HASH_RESPONSE, XmlResolver.HASH_DEFINATION, sender.from.identification, XmlResolver.bizzGroupId(sender.from.identification));
		response.sender = sender;
		return response;
	}

	public static XmlFile link(XmlFileHeader sender) {
		XmlFile response = new XmlFile();
		response.creator = XmlFileHeader.creator(XmlResolver.LINK_RESPONSE, XmlResolver.LINK_DEFINATION, sender.from.identification, XmlResolver.bizzGroupId(sender.from.identification));
		response.sender = sender;
		return response;
	}

	public static XmlFile erro(XmlFileHeader sender) {
		XmlFile response = new XmlFile();
		response.creator = XmlFileHeader.creator(XmlResolver.ERRO_RESPONSE, XmlResolver.ERRO_DEFINATION, sender.from.identification, XmlResolver.bizzGroupId(sender.from.identification));
		response.sender = sender;
		return response;
	}

	public List<XmlBizGrp> getBizGrp() {
		return bizGrps;
	}

	public XmlFileHeader creator() {
		return creator;
	}

	public String operation() {
		return creator.fileType;
	}

	public int quantity() {
		return bizGrps.size();
	}

	public String category() {
		return creator.from.identification.contains("-") ? creator.from.identification.split("-")[0] : "-1";
	}

	public String participant() {
		return creator.from.identification.contains("-") ? creator.from.identification.split("-")[1] : "-999";
	}

	public String fileName() {
		return creator.fileType + "_" + creator.bizzGroupId + ".xml";
	}

	public void add(XmlBizGrp bizGrp) {
		bizGrps.add(bizGrp);
	}

	public void addAll(List<XmlBizGrp> bizGrps) {
		bizGrps.addAll(bizGrps);
	}

}
