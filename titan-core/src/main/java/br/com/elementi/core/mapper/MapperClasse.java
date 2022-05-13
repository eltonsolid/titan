package br.com.elementi.core.mapper;

import java.util.Set;

import com.google.common.collect.Sets;

@Deprecated
public class MapperClasse {

	private Class<?> from;
	private Set<MapperTemplate> templates;

	private MapperClasse() {
		templates = Sets.newHashSet();
	}

	private MapperClasse(Class<?> from) {
		this();
		this.from = from;
	}

	public static MapperClasse from(Class<?> from) {
		return new MapperClasse(from);
	}

	public void add(MapperTemplate template) {
		verify(template);
		templates.add(template);
	}

	private void verify(MapperTemplate template) {
		if (isNotSameFrom(template)) {
			throw new IllegalArgumentException();
		}
	}

	private boolean isNotSameFrom(MapperTemplate template) {
		return !this.from.equals(template.getFrom());
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof MapperClasse) {
			MapperClasse that = (MapperClasse) obj;
			return this.from.equals(that.from);
		}
		return false;
	}

	@Override
	public int hashCode() {
		return from.hashCode();
	}

	public MapperTemplate find(Class<?> to) {
		for (MapperTemplate template : templates) {
			if (template.getTo().equals(to)) {
				return template;
			}
		}
		return createTemplateAndAdd(to);
	}

	private MapperTemplate createTemplateAndAdd(Class<?> to) {
		MapperTemplate template = MapperTemplate.with(from, to);
		templates.add(template);
		return template;
	}

	public Integer size() {
		return templates.size();
	}

	public Class<?> getFrom() {
		return from;
	}

	public Set<MapperTemplate> getTemplates() {
		return templates;
	}

}
