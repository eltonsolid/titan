package br.com.elementi.core.soap;

public class MySocketEncode {//implements Encoder.Text<PersonSocket>, Decoder.Text<PersonSocket> {

	/*@Override
	public void init(EndpointConfig config) {
		// TODO Auto-generated method stub

	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
	}

	@Override
	public boolean willDecode(String s) {
		return (s != null);
	}

	@Override
	public PersonSocket decode(String s) throws DecodeException {
		System.out.println("Incoming XML " + s);
		PersonSocket person = null;
		JAXBContext jaxbContext;
		try {
			jaxbContext = JAXBContext.newInstance(PersonSocket.class);
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			StringReader reader = new StringReader(s);
			person = (PersonSocket) unmarshaller.unmarshal(reader);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return person;

	}

	@Override
	public String encode(PersonSocket object) throws EncodeException {
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(PersonSocket.class);
			javax.xml.bind.Marshaller marshaller = jaxbContext.createMarshaller();
			StringWriter st = new StringWriter();
			marshaller.marshal(object, st);
			System.out.println("OutGoing XML " + st.toString());
			return st.toString();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return "";

	}*/

}
