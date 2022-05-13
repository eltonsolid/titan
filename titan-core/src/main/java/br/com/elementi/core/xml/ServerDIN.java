package br.com.elementi.core.xml;

public class ServerDIN {

	private DIN din;
	private String receivePath = "./Performance/";
	private String responsePath = "./output/";

	public ServerDIN(DIN din) {
		this.din = din;
	}

	public void call(String file) {
		try {
			XmlFile receive = Xml.read(receivePath + file);
			XmlFile response = new XmlProcess().start(receive);
			Xml.write(response, responsePath + response.fileName());
			din.call(responsePath + response.fileName());
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

}
