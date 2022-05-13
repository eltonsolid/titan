package br.com.elementi.core.qtable;

public enum QOperator {
	ALIAS("alias"), VIRG(", "), SELECT("SELECT "), FROM("FROM "), WHERE(" WHERE "), AND(" AND "), BETWEEN(" BETWEEN "), EQUAL(" = "), LIKE(
			" LIKE "), OR(" OR "), GREATER(" >= "), LESSER(" <= "),IN(" IN "), COUNT("COUNT "), FETCH(" FETCH ALL "), DISTINCT("DISTINCT ");

	private final String operator;

	private QOperator(String description) {
		this.operator = description;
	}

	public String operator() {
		return operator;
	}

	@Deprecated
	public String operator(String field) {
		return "";
	}
	
	@Deprecated
	public static String alias(String field) {
		return ALIAS.operator.trim() + "." + field + " ";
	}

}
