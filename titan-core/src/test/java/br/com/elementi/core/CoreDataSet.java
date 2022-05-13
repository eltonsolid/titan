package br.com.elementi.core;

import br.com.elementi.core.schema.DataSet;
import br.com.elementi.core.schema.Table.Key;

public class CoreDataSet {

	public static final Key APPLICATION_1 = CoreSchema.application().key(1);
	public static final Key APPLICATION_2 = CoreSchema.application().key(2);
	public static final Key DOCUMENT_1 = CoreSchema.document().key(1);
	public static final Key DOCUMENT_2 = CoreSchema.document().key(2);
	public static final Key PERSON_1 = CoreSchema.person().key(1);

	public static DataSet applicationDataset() throws Exception {
		DataSet dataSet = DataSet.create("application");
		dataSet.add(CoreSchema.application().row(APPLICATION_1, "Test For Name", "Description"));
		dataSet.add(CoreSchema.application().row(APPLICATION_2, "Test For Name2", "Description2"));
		dataSet.add(CoreSchema.document().row(DOCUMENT_1, APPLICATION_1, "111456789", "For Application1"));
		dataSet.add(CoreSchema.document().row(DOCUMENT_2, APPLICATION_2, "222456789", "For Application2"));
		dataSet.add(CoreSchema.document().row(3, APPLICATION_1, "111456789", "For Application3"));
		dataSet.add(CoreSchema.document().row(4, APPLICATION_2, "222456789", "For Application4"));
		return dataSet;
	}

	public static DataSet personDataset() throws Exception {
		DataSet applicationDataset = applicationDataset();
		applicationDataset.add(CoreSchema.person().row(PERSON_1, APPLICATION_1, DOCUMENT_2, "Eltonsolid", "For Application2"));
		return applicationDataset;
	}

}
