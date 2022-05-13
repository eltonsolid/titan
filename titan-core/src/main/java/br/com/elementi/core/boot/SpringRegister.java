package br.com.elementi.core.boot;

import java.lang.annotation.Annotation;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import br.com.elementi.core.resource.Storage;
import br.com.elementi.core.tools.Message;
import br.com.elementi.core.tools.MessageTemplate;
import br.com.elementi.core.tools.Reflect;

import com.google.common.collect.Lists;

@Configuration
public class SpringRegister {

	@Resource
	private AnnotationConfigApplicationContext context;

	@Autowired
	private Storage storage;

	@PostConstruct
	public void register() throws Exception {
		registerBeansDomain();
	}

	@SuppressWarnings("unchecked")
	private void registerBeansDomain() throws Exception {
		for (Class<?> annotatedClasse : with(Repository.class, Service.class, Component.class)) {
			context.register(annotatedClasse);
		}
	}

	private List<Class<?>> with(@SuppressWarnings("unchecked") Class<? extends Annotation>... annotations) throws Exception {
		return storage.listWithAnnotation(Lists.newArrayList(annotations));
	}
	
	@Bean
	public Message message() throws Exception {
		List<MessageTemplate> templates = Lists.newArrayList();
		List<Class<MessageTemplate>> interfaces = storage.listWithInterface(MessageTemplate.class);
		for (Class<MessageTemplate> classe : interfaces) {
			templates.add((MessageTemplate) Reflect.instance(classe));
		}
		return Message.create(templates);

	}

}
