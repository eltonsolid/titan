package br.com.elementi.core.boot;

import java.util.List;
import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.MappedSuperclass;

import org.hibernate.SessionFactory;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.AnnotationTransactionAttributeSource;
import org.springframework.transaction.interceptor.TransactionAttributeSourceAdvisor;
import org.springframework.transaction.interceptor.TransactionInterceptor;

import com.google.common.collect.Lists;

import br.com.elementi.core.annotation.HibernateTypeResolver;
import br.com.elementi.core.annotation.IdentityDefine;
import br.com.elementi.core.domain.DomainType;
import br.com.elementi.core.hibernate.IdentityType;
import br.com.elementi.core.model.Identity;
import br.com.elementi.core.resource.Storage;
import br.com.elementi.core.tools.Reflect;

@Configuration
public class HibernateRegister {

	LocalSessionFactoryBuilder factoryBuilder = new LocalSessionFactoryBuilder(null,
			new PathMatchingResourcePatternResolver());

	@Autowired
	@Qualifier(value = "hibernateProperties")
	private Properties properties;

	@Autowired
	private Storage storage;

	private SessionFactory sessionFactory;

	@PostConstruct
	public void register() throws Exception {
		registerEntities(factoryBuilder);
		factoryBuilder.addProperties(properties);
		registerUserTypes(factoryBuilder);
		sessionFactory = factoryBuilder.buildSessionFactory();
	}

	@SuppressWarnings("unchecked")
	private void registerUserTypes(LocalSessionFactoryBuilder hibernateResolver) throws Exception {
		List<Class<?>> resolvers = storage.listWithAnnotation(HibernateTypeResolver.class);
		for (Class<?> resolver : resolvers) {
			DomainType<?, ?> domainType = (DomainType<?, ?>) Reflect.instance(resolver);
			hibernateResolver.registerTypeOverride(domainType, domainType.getKeys());
		}

		List<Class<?>> withAnnotation = storage.listWithAnnotation(IdentityDefine.class);
		for (Class<?> identity : withAnnotation) {
			hibernateResolver.registerTypeOverride(new IdentityType((Class<? extends Identity>) identity),
					new String[] { identity.getName() });
		}
	}

	private LocalSessionFactoryBuilder registerEntities(LocalSessionFactoryBuilder factoryBuilder) throws Exception {
		return factoryBuilder.addAnnotatedClasses(listAnnotatedEntities().toArray(new Class<?>[0]));
	}

	@Bean
	public SessionFactory localSessionFactoryBean() throws Exception {
		return sessionFactory;
	}

	@Bean
	public TransactionAttributeSourceAdvisor transactionAttributeSourceAdvisor() throws Exception {
		HibernateTransactionManager manager = new HibernateTransactionManager();
		manager.setSessionFactory(sessionFactory);
		AnnotationTransactionAttributeSource attribute = new AnnotationTransactionAttributeSource();
		TransactionInterceptor interceptor = new TransactionInterceptor(manager, attribute);
		return new TransactionAttributeSourceAdvisor(interceptor);
	}

	@Bean
	public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
		return new DefaultAdvisorAutoProxyCreator();
	}

	@SuppressWarnings("unchecked")
	private List<Class<?>> listAnnotatedEntities() throws Exception {
		return storage.listWithAnnotation(Lists.newArrayList(Entity.class, Embeddable.class, MappedSuperclass.class));
	}

}
