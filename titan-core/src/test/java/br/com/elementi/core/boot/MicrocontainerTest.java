package br.com.elementi.core.boot;

import static org.junit.Assert.assertNotNull;

import java.util.Properties;

import org.hibernate.SessionFactory;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.interceptor.TransactionAttributeSourceAdvisor;

import br.com.elementi.core.application.ApplicationRepository;
import br.com.elementi.core.application.ApplicationService;
import br.com.elementi.core.resource.Storage;

public class MicrocontainerTest {

	private static AnnotationConfigApplicationContext applicationContext;

	@BeforeClass
	public static void before() throws Exception {
		applicationContext = Microcontainer.forDeveloper();
	}

	@Test
	public void testDataSource() throws Exception {
		Properties properties = applicationContext.getBean("hibernateProperties", Properties.class);
		assertNotNull(properties);
	}

	@Test
	public void testStorage() throws Exception {
		Storage storage = applicationContext.getBean(Storage.class);
		assertNotNull(storage);
	}

	@Test
	public void testHibernateProperties() throws Exception {
		Properties properties = (Properties) applicationContext.getBean("hibernateProperties");
		assertNotNull(properties);
	}

	@Test
	public void testLocalSessionFactoryBean() throws Exception {
		SessionFactory sessionFactoryBean = applicationContext.getBean(SessionFactory.class);
		assertNotNull(sessionFactoryBean);
	}

	@Test
	public void testTransactionAttributeSourceAdvisor() throws Exception {
		TransactionAttributeSourceAdvisor advisor = applicationContext.getBean(TransactionAttributeSourceAdvisor.class);
		assertNotNull(advisor);
	}

	@Test
	public void testDefaultAdvisorAutoProxyCreator() throws Exception {
		DefaultAdvisorAutoProxyCreator proxyCreator = applicationContext.getBean(DefaultAdvisorAutoProxyCreator.class);
		assertNotNull(proxyCreator);
	}

	@Test
	public void testService() throws Exception {
		ApplicationService service = applicationContext.getBean(ApplicationService.class);
		assertNotNull(service);
	}

	@Test
	public void testRepository() throws Exception {
		ApplicationRepository repository = applicationContext.getBean(ApplicationRepository.class);
		assertNotNull(repository);
	}

}
