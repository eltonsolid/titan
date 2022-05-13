package br.com.elementi.core.soap;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author eltonsolid
 *
 */
@XmlRootElement
public class PersonSocket {

	private String name;
	private String subname;

	public PersonSocket() {
		System.out.println("PersonSocket.PersonSocket()");
	}

	public PersonSocket(String name, String subname) {
		this.name = name;
		this.subname = subname;
	}

	public String getName() {
		return name;
	}

	public String getSubname() {
		return subname;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setSubname(String subname) {
		this.subname = subname;
	}

	@Override
	public String toString() {
		return "PersonSocket [name=" + name + ", subname=" + subname + "]";
	}

}
