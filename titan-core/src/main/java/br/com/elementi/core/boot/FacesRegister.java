package br.com.elementi.core.boot;

import java.util.List;
import java.util.Locale;

import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import br.com.elementi.core.context.Context;
import br.com.elementi.core.resource.Storage;
import br.com.elementi.core.tools.Message;
import br.com.elementi.core.view.ActionHandle;

import com.sun.faces.application.ApplicationImpl;
import com.sun.faces.mgbean.BeanManager;
import com.sun.faces.mgbean.ManagedBeanBuilder;
import com.sun.faces.mgbean.ManagedBeanInfo;

@Configuration
public class FacesRegister {

	@Autowired
	private ApplicationImpl application;

	@Autowired
	private Storage storage;

	@Autowired
	private BeanManager manager;

	@Autowired
	private Message message;

	@PostConstruct
	public void configure() throws Exception {
		application.setDefaultLocale(new Locale("pt", "BR"));
		application.setActionListener(new ActionHandle(message));
		registerManagedBean();
		registerMessage();
		registerSpringIntegration();
	}

	private void registerMessage() {

	}

	private void registerSpringIntegration() {
		manager.getRegisteredBeans().put("message", managerBeanBuilder(message.messageMap()));
	}

	public void registerManagedBean() throws Exception {
		List<Class<?>> beans = storage.listWithAnnotation(ManagedBean.class);
		for (Class<?> bean : beans) {
			manager.register(managerBean(bean));
		}
	}

	private <T> ManagedBeanBuilder managerBeanBuilder(T instance) {
		ManagedBeanBuilder beanBuilder = new ManagedBeanBuilder(managerBean(instance.getClass())) {
			@Override
			protected Object newBeanInstance() {
				return instance;
			}
		};
		return beanBuilder;
	}

	//TODO Não remover este metodo, ele vai ser utilizado para registrar os MBs no sprin
	//Assim altomaticamente vamos ter um MB new instance, vindo diretamente do Spring pelo Context.getBean
	// LEMBRETE
	private ManagedBeanBuilder managerBeanBuilder(Class<?> beanClass) {
		ManagedBeanBuilder beanBuilder = new ManagedBeanBuilder(managerBean(beanClass)) {
			@Override
			protected Object newBeanInstance() {
				return Context.getBean(beanClass);
			}
		};
		return beanBuilder;
	}

	private ManagedBeanInfo managerBean(Class<?> beanClass) {
		return new ManagedBeanInfo(managerName(beanClass), beanClass.getName(), scope(beanClass), null, null, null, null);
	}

	private String managerName(Class<?> beanClass) {
		String value = beanClass.getSimpleName();
		return value.substring(0, 1).toLowerCase() + value.substring(1);
	}

	private String scope(Class<?> beanClass) {
		if (beanClass.isAnnotationPresent(RequestScoped.class)) {
			return "request";
		}
		if (beanClass.isAnnotationPresent(SessionScoped.class)) {
			return "session";
		}
		if (beanClass.isAnnotationPresent(ViewScoped.class)) {
			return "view";
		}
		if (beanClass.isAnnotationPresent(ApplicationScoped.class)) {
			return "application";
		}
		return "request";

	}

}
