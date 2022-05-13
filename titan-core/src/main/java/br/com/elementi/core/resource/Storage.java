package br.com.elementi.core.resource;

import java.lang.annotation.Annotation;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import com.google.common.base.Supplier;
import com.google.common.collect.ListMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimaps;

public class Storage {

	private ListMultimap<String, String> classesWithAnnotation;
	private ListMultimap<String, String> classesWithInterface;

	private Storage() {
		classesWithAnnotation = multimaps();
		classesWithInterface = multimaps();
	}

	private ListMultimap<String, String> multimaps() {
		return Multimaps.newListMultimap(new HashMap<String, Collection<String>>(), new Supplier<List<String>>() {
			public List<String> get() {
				return Lists.newArrayList();
			}
		});
	}

	public static Storage store(Resources resources) throws Exception {
		List<ResourceStream> resourceStreams = resources.filterClassFiles();
		return new Storage().classFile(resourceStreams);
	}

	private Storage classFile(List<ResourceStream> resourceStreams) throws Exception {
		for (ResourceStream stream : resourceStreams) {
			classFile(stream);
		}
		return this;
	}

	private void classFile(ResourceStream file) throws Exception {
		ClassStream classStream = ClassStream.create(file);
		store(classStream);
	}

	private void store(ClassStream classStream) {
		classesWithAnnotations(classStream);
		classesWithInterfacess(classStream);
	}

	private void classesWithAnnotations(ClassStream classStream) {
		for (String annotation : classStream.annotation()) {
			classesWithAnnotation.put(annotation, classStream.name());
		}
	}

	private void classesWithInterfacess(ClassStream classStream) {
		for (String interfaces : classStream.interfaces()) {
			classesWithInterface.put(interfaces, classStream.name());
		}
	}

	@SuppressWarnings("unchecked")
	public List<Class<?>> listWithAnnotation(final Class<? extends Annotation> annotation) throws Exception {
		List<Class<? extends Annotation>> itens = Lists.<Class<? extends Annotation>> newArrayList(annotation);
		return listWithAnnotation(itens);
	}

	public List<Class<?>> listWithAnnotation(final List<Class<? extends Annotation>> annotations) throws Exception {
		List<Class<?>> itens = Lists.newArrayList();
		for (Class<? extends Annotation> annotation : annotations) {
			List<String> classeNames = classesWithAnnotation.get(annotation.getName());
			itens.addAll(loadClasses(classeNames));
		}
		return itens;
	}
	
	public <I> List<Class<I>> listWithInterface(Class<I> interfaceI) throws Exception {
		List<Class<I>> loaded = Lists.<Class<I>> newArrayList();
		List<String> classeNames = classesWithInterface.get(interfaceI.getName());
		loaded.addAll(loadClasses(classeNames));
		return loaded;
	}

	@SuppressWarnings("unchecked")
	private <T> List<Class<T>> loadClasses(List<String> classeNames) throws Exception {
		List<Class<T>> classes = Lists.newArrayList();
		for (String name : classeNames) {
			classes.add((Class<T>) Thread.currentThread().getContextClassLoader().loadClass(name));
		}
		return classes;
	}

}
