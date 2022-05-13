package br.com.elementi.test;

import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.ws.BindingType;

@WebService(serviceName = "DummyWebService", portName = "DummyWebServicePort")
@BindingType(value = "http://java.sun.com/xml/ns/jaxws/2003/05/soap/bindings/HTTP/")
public interface DummyWebService {

	public String sayHi(@WebParam(name = "text") String text);

}
