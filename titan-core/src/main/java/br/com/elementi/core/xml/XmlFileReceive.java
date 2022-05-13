package br.com.elementi.core.xml;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.eclipse.persistence.oxm.annotations.XmlPath;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "Document")
@Deprecated
public class XmlFileReceive {

	@XmlPath("BizFileHdr/Xchg/BizGrpDesc")
	private XmlFileHeader creator;

	@XmlPath("BizFileHdr/Xchg/BizGrp")
	private List<XmlBizGrp> bizGrp;

	public List<XmlBizGrp> getBizGrp() {
		return bizGrp;
	}

	public XmlFileHeader creator() {
		return creator;
	}

	public String operation() {
		return creator.fileType;
	}

	public int quantity() {
		return bizGrp.size();
	}

	public String category() {
		return creator.from.identification.contains("-") ? creator.from.identification.split("-")[0] : "-1";
	}

	public String participant() {
		return creator.from.identification.contains("-") ? creator.from.identification.split("-")[1] : "-999";
	}

}
