package br.com.elementi.core.context;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import br.com.elementi.core.boot.Microcontainer;

//TODO O processo dentro do SERVER, precisa de mais estudos, acredito que uma classe que contenha o contexto em tempo de execução seja algo bem intessante
//para a produlçao, algo do tipo uma classe com muitas variasveis staticas, mas apenas um ponto de inicialização.
public class Context {
	private static AnnotationConfigApplicationContext applicationContext;

	public static void startForDeveloper() throws Exception {
		applicationContext = Microcontainer.forDeveloper();
	}

	public static void startForServer() throws Exception {
		applicationContext = Microcontainer.forServer();
	}

	public static <T> T getBean(Class<T> requiredType) {
		return applicationContext.getBean(requiredType);
	}

	public AnnotationConfigApplicationContext applicationContext() {
		return applicationContext;
	}
}
