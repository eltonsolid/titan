package br.com.elementi.core.boot;

import java.util.Properties;

import javax.faces.FactoryFinder;
import javax.faces.application.ApplicationFactory;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import br.com.elementi.core.resource.Resources;
import br.com.elementi.core.resource.Storage;
import br.com.elementi.core.tools.Reflect;

import com.sun.faces.application.ApplicationAssociate;
import com.sun.faces.application.ApplicationImpl;
import com.sun.faces.mgbean.BeanManager;
import com.sun.faces.mgbean.ManagedBeanInfo;

@Configuration
public class BootRegister {

	@Configuration
	@Profile(value = Microcontainer.DEV)
	public static class DEV {

		@Bean(name = "hibernateProperties")
		public Properties properties() {
			Properties properties = new Properties();
			properties.put("hibernate.show_sql", "true");
			properties.put("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
			properties.put("hibernate.connection.url", "jdbc:h2:./src/test/resources/database/test");
			properties.put("hibernate.connection.username", "");
			properties.put("hibernate.connection.password", "");
			return properties;
		}

		@Bean(name = "soapProperties")
		public Properties soapProperties() {
			Properties properties = new Properties();
			properties.put("endpoint", "http://localhost:9000");
			return properties;
		}

		@Bean(name = "passwordProperties")
		public Properties passwordProperties() {
			Properties properties = new Properties();
			properties.put("ws-client", "127.0.0.1");
			return properties;
		}

		@Bean
		public BeanManager beanManager() {
			return new BeanManager(null, false) {
				@Override
				public void register(ManagedBeanInfo beanInfo) {

				}

			};
		}

		@Bean
		public ApplicationImpl application() throws Exception {
			return Reflect.forceInstance(ApplicationImpl.class);
		}

	}

	@Configuration
	@Profile(value = Microcontainer.SERVER)
	public static class SERVER {

		@Bean(name = "hibernateProperties")
		public Properties properties() {
			Properties properties = new Properties();
			properties.put("hibernate.show_sql", "true");
			properties.put("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
			properties.put("hibernate.connection.url", "jdbc:h2:./src/test/resources/database/test");
			properties.put("hibernate.connection.username", "");
			properties.put("hibernate.connection.password", "");
			/*properties.put("hibernate.show_sql", "true");
			properties.put("hibernate.dialect", "org.hibernate.dialect.Oracle12gDialect");
			properties.put("hibernate.connection.url", "jdbc:oracle:thin:@127.0.0.1:1521:xe");
			properties.put("hibernate.connection.username", "titan");
			properties.put("hibernate.connection.password", "titan");*/
			return properties;
		}

		@Bean
		public BeanManager beanManager() {
			return ApplicationAssociate.getCurrentInstance().getBeanManager();
		}

		@Bean
		public ApplicationImpl application() {
			return (ApplicationImpl) ((ApplicationFactory) FactoryFinder.getFactory(FactoryFinder.APPLICATION_FACTORY))
					.getApplication();
		}
	}

	@Bean
	public Storage store() throws Exception {
		return Storage.store(Resources.allWithContext("META-INF/domain/domain.xml"));
	}

}
