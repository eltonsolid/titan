package br.com.elementi.core.schema;

import java.util.LinkedList;
import java.util.List;

import com.google.common.collect.Lists;

import br.com.elementi.core.schema.Table.Key;
import br.com.elementi.core.schema.Table.Value;

public class Insert {

	/*private Table table;
	private LinkedList<Value> values;

	private Insert(Table table) {
		this.table = table;
		this.values = Lists.newLinkedList();
	}

	public static Insert objects(Table table, Key key, Object... objects) {
		return new Insert(table).defaulValues().join(key, objects);
	}

	private Insert join(Key key, Object[] objects) {
		List<Value> newValues = newValues(objects);
		newValues.add(new Value(key.column, key.value));
		return merge(newValues);
	}

	private Insert defaulValues() {
		this.table.columns().forEach(column -> values.add(new Value(column, column.defaultValue())));
		return this;
	}

	private List<Value> newValues(Object... objects) {
		List<Value> newValues = Lists.newArrayList();
		int index = 1;
		for (Object object : objects) {
			Column column = table.column(index++);
			newValues.add(new Value(column, object));
		}
		return newValues;
	}

	public Insert merge(List<Value> newValues) {
		for (Value newValue : newValues) {
			for (Value value : values) {
				if (value.column.name().equals(newValue.column.name())) {
					value.value = newValue.value;
				}
			}
		}
		return this;
	}

	public String query() {
		StringBuilder names = new StringBuilder();
		StringBuilder values = new StringBuilder();
		String node = "";
		for (Value value : this.values) {
			names.append(node + value.column.name());
			values.append(node + value.converted());
			node = ", ";
		}
		return "INSERT INTO " + table.getTableName() + "(" + names + ")VALUES(" + values + ")";
	}*/

	// TODO não preciso nem saber a posição, por que não importa mais. Preciso
	// apenas adicionar a lista os itens de interesse
	// o metodo na verdde precisa apenas achar as colunas e depois adicionar na
	// lista, mas devemo remover o columnvalue anterior
	// isso pode ser feito pelo nome da coluna
	// com isso posso me desacoplar da ormde e utilizar a stram livremente.
	//
	// FEITO: A ideia foi perfeita!!!!
	/*public Insert set(Integer position, Object... objects) {
		return merge(values.subList(position - 1, values.size()), objects);
	}*/

}
