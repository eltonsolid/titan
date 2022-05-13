package br.com.elementi.core.soap;

import javax.jws.WebService;

@WebService
public class PersonCreateWebServiceImpl {

	public void sayOk(String name) {
		System.out.println("WEB SERVICE" + name);
	}
}
