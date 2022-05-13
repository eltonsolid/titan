package br.com.elementi.core.boot;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Microcontainer {

	public static final String DEV = "DEV";
	public static final String SERVER = "SERVER";

	public static AnnotationConfigApplicationContext forDeveloper() throws Exception {
		return new Microcontainer().bootStrap(DEV);
	}

	public static AnnotationConfigApplicationContext forServer() throws Exception {
		return new Microcontainer().bootStrap(SERVER);
	}

	private AnnotationConfigApplicationContext bootStrap(String profile) throws Exception {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.getEnvironment().addActiveProfile(profile);
		register(context);
		return context;
	}

	private void register(AnnotationConfigApplicationContext context) {
		context.register(BootRegister.class);
		context.register(HibernateRegister.class);
		context.register(SpringRegister.class);
		context.register(FacesRegister.class);
		context.refresh();
	}

}
