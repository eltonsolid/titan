package br.com.elementi.core.mapper;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.function.Function;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;

public class MapperField implements Iterable<MapperNode> {

	private LinkedList<MapperNode> itens;
	private MapperFieldConverter converter;

	private MapperField(LinkedList<MapperNode> itens, MapperFieldConverter converter) {
		this.itens = itens;
		this.converter = converter;
	}

	public static MapperField of(LinkedList<MapperNode> nodes) throws Exception {
		return new MapperField(nodes, conveter -> conveter);
	}

	public static MapperField onField(String value) throws Exception {
		LinkedList<MapperNode> itens = create(value, MapperNode::onField);
		return new MapperField(itens, converter -> converter);
	}

	public static MapperField onGet(String value) throws Exception {
		LinkedList<MapperNode> itens = create(value, MapperNode::onGet);
		return new MapperField(itens, converter -> converter);
	}

	public MapperField with(MapperFieldConverter converter) {
		return new MapperField(itens, converter);
	}

	public MapperFieldConverter converter() {
		return converter;
	}

	private static LinkedList<MapperNode> create(String value, Function<String, MapperNode> function) {
		LinkedList<MapperNode> itens = Lists.newLinkedList();
		for (String split : value.split("\\.")) {
			itens.add(function.apply(split));
		}
		return itens;
	}

	public String name() {
		return Joiner.on(".").join(itens);
	}

	public Integer size() {
		return itens.size();
	}

	public Boolean hasValue() {
		return !itens.isEmpty();
	}

	public MapperNode get(int position) {
		return itens.get(position);
	}

	public Iterator<MapperNode> iterator() {
		return itens.iterator();
	}

	public MapperNode lastNode() {
		return itens.getLast();
	}

	public String lastNameForGet() throws Exception {
		return itens.getLast().nameForGet();
	}

	public String lastNameForSet() throws Exception {
		return itens.getLast().nameForSet();
	}

	@Override
	public int hashCode() {
		return name().hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof MapperField) {
			MapperField that = (MapperField) obj;
			return this.name().equals(that.name());
		}
		return false;
	}

}
