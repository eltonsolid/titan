package br.com.elementi.core.context;

import java.io.IOException;

import javax.faces.webapp.FacesServlet;
import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class WebContext implements Servlet {

	private FacesServlet facesServlet;

	public WebContext() {
		facesServlet = new FacesServlet();
	}

	public void init(ServletConfig servletConfig) throws ServletException {
		facesServlet.init(servletConfig);
		startContext();
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> WebContext <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
	}

	private void startContext() {
		try {
			Context.startForServer(); 
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ServletConfig getServletConfig() {
		return facesServlet.getServletConfig();
	} 

	public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
		facesServlet.service(request, response);
	}

	public String getServletInfo() {
		return facesServlet.getServletInfo();
	}

	public void destroy() {
		facesServlet.destroy();
	}

}
