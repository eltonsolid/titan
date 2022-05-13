package br.com.elementi.core.application;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import br.com.elementi.core.CoreDataSet;
import br.com.elementi.core.CoreSchema;
import br.com.elementi.core.Integration;
import br.com.elementi.core.model.Identity;
import br.com.elementi.core.schema.Schema;

public class ApplicationServiceTest extends Integration<ApplicationService> {

	@Override
	public void schemas(Schema schema) throws Exception {
		schema.add(CoreDataSet.applicationDataset());
		schema.add(CoreSchema.application().row(3));
		schema.clear(CoreSchema.application().row(FOUR));
		schema.clear(CoreSchema.application().row(FIVE));
	}

	@Test
	public void testListApplication() throws Exception {
		List<Application> applications = service().listApplication();
		assertEquals(TREE.intValue(), applications.size());
	}

	@Test
	public void testListApplicationWithDescription() throws Exception {
		List<Application> applications = service().listApplication("Test For Name");
		assertEquals(CoreDataSet.APPLICATION_1.Get(), applications.get(0).getId().getValue().toString());
	}

	@Test
	public void testListDocument() throws Exception {
		List<ApplicationDocument> documents = service().listDocument();
		assertEquals(FOUR.intValue(), documents.size());
	}

	@Test
	public void testListDocumentCombinateOr() throws Exception {
		List<ApplicationDocument> documents = service().listDocumentCombinateOr();
		assertEquals(TWO.intValue(), documents.size());
	}

	@Test
	public void testListDocumentFromBuilder() throws Exception {
		List<ApplicationDocument> documents = service().listDocumentFromBuilder();
		assertEquals(CoreDataSet.DOCUMENT_1.Get(), documents.get(0).getId().getValue().toString());
	}

	@Test
	public void testInsertApplication() throws Exception {
		Application application = Application.create(Identity.create(FOUR)).name("Eltonsolid").get();
		service().insert(application);
		List<Application> applications = service().listApplication();
		assertEquals(FOUR.intValue(), applications.size());
	}

	@Test
	public void testUpdateApplication() throws Exception {
		Application application = Application.create(Identity.ONE).name("10!!!!L").get();
		service().update(application);
		Application found = service().find(ONE);
		assertEquals("10!!!!L", found.getName());
	}

}
