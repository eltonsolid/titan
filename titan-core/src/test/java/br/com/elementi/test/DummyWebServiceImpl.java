package br.com.elementi.test;

import javax.annotation.Resource;
import javax.jws.WebService;
import javax.servlet.http.HttpServletRequest;
import javax.xml.ws.BindingType;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.elementi.core.application.ApplicationService;

@WebService(serviceName = "DummyWebService", portName = "DummyWebServicePort")
@BindingType(value = "http://java.sun.com/xml/ns/jaxws/2003/05/soap/bindings/HTTP/")
public class DummyWebServiceImpl implements DummyWebService {

	@Autowired
	private ApplicationService service;

	@Resource
	private WebServiceContext context;

	public String sayHi(String text) {
		HttpServletRequest request = (HttpServletRequest) context.getMessageContext().get(
				MessageContext.SERVLET_REQUEST);
		System.out.println("IP: " + request.getRemoteAddr() + ", Port: " + request.getRemotePort() + ", Host: "
				+ request.getRemoteHost());
		System.out.println("::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::: " + text);
		return text;
	}
}
