package br.com.elementi.core.resource;

import java.io.DataInputStream;
import java.util.List;

import javassist.bytecode.AnnotationsAttribute;
import javassist.bytecode.ClassFile;
import javassist.bytecode.annotation.Annotation;

import com.google.common.collect.Lists;

public class ClassStream {

	private final ClassFile classFile;

	public ClassStream(ClassFile classFile) {
		this.classFile = classFile;
	}

	public static ClassStream create(ResourceStream stream) throws Exception {
		DataInputStream inputStream = new DataInputStream(stream.getStream());
		ClassFile classFile = new ClassFile(inputStream);
		return new ClassStream(classFile);
	}

	public String[] interfaces() {
		String[] interfaces = classFile.getInterfaces();
		return interfaces == null ? new String[] {} : interfaces;
	}

	public List<String> annotation() {
		List<String> names = Lists.newArrayList();
		for (Annotation annotation : annotationsAttribute()) {
			names.add(annotation.getTypeName());
		}
		return names;
	}

	private Annotation[] annotationsAttribute() {
		AnnotationsAttribute attribute = (AnnotationsAttribute) classFile.getAttribute(AnnotationsAttribute.visibleTag);
		if (attribute == null) {
			return new Annotation[] {};
		}
		Annotation[] annotations = attribute.getAnnotations();
		return annotations;
	}

	public String name() {
		return classFile.getName();
	}

	@Override
	public String toString() {
		return name();
	}

}
