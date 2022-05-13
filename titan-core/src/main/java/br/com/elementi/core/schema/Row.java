package br.com.elementi.core.schema;

public abstract class Row implements Delete {

	public static final Row NULL = new Row() {

		@Override
		public String insert() {
			return "INSERT NULL";
		}

		@Override
		public String delete() {
			return "DELETE NULL";
		}
	};

	public abstract String insert();

	public abstract String delete();

	@Override
	public final int hashCode() {
		return insert().hashCode();
	}

	@Override
	public final boolean equals(Object obj) {
		if (obj instanceof Row) {
			Row that = (Row) obj;
			return this.insert().equals(that.insert());
		}
		return false;
	}

}
