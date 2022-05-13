package br.com.elementi.core.xml;

public class XmlReceiver{

	private FPB fpb;
	private XmlProcess xmlProcess;
	private String receivePath = "./Performance/";
	private String responsePath = "./output/";

	public XmlReceiver(FPB fpb) {
		this.xmlProcess = new XmlProcess();
		this.fpb = fpb;
	}

	public void call(String path) {
		try {
			XmlFile fileReceive = Xml.read(receivePath + path);
			XmlFile fileResponse = xmlProcess.start(fileReceive);
			Xml.write(fileResponse, responsePath + fileResponse.fileName());
			fpb.call(responsePath + fileResponse.fileName());
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

}
