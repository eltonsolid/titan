package br.com.elementi.core.boot;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;

import br.com.elementi.core.resource.Storage;

@Configuration
public class SoapRegister {

	@Autowired
	private Storage storage;

	@Autowired
	@Qualifier(value = "soapProperties")
	private Properties soapProperties;

	@Autowired
	@Qualifier(value = "passwordProperties")
	private Properties passwordProperties;

	/*@Bean(destroyMethod = "shutdown")
	public SoapEndpoint endpoint() throws Exception {
		SoapEndpoint soapEndpoint = new SoapEndpoint(soapProperties, passwordProperties);
		for (Class<?> classe : storage.listWithAnnotation(WebService.class)) {
			if (isNotInterface(classe)) {
				soapEndpoint.add(classe);
			}
		}
		return soapEndpoint.process();
	}*/

	private boolean isNotInterface(Class<?> classe) {
		return !classe.isInterface();
	}

}
