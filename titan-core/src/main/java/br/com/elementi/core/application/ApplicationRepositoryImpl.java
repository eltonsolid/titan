package br.com.elementi.core.application;

import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.elementi.core.application.ApplicationDocument.Builder;
import br.com.elementi.core.domain.DomainRepository;

@Repository
public class ApplicationRepositoryImpl extends DomainRepository<Application.Builder>implements ApplicationRepository {

	@Override
	public Application find(ApplicationIdentity identity) throws Exception {
		return table().find(identity);
	}

	public List<Application> listApplication() throws Exception {
		return table().list();
	}

	@Override
	public List<Application> listApplication(String name) throws Exception {
		return entity().name(name).list();
	}

	public void insert(Application application) {
		saveEntity(application);
	}

	public void update(Application application) {
		updateEntity(application);
	}

	@Override
	public List<ApplicationDocument> listDocument() throws Exception {
		return tableOf(ApplicationDocument.class).list();
	}

	@Override
	public List<ApplicationDocument> listDocumentCombinateOr() throws Exception {
		return tableOf(ApplicationDocument.class).eq("code", "111456789").eq("description", "For Application1").or()
				.eq("code", "111456789").eq("description", "For Application3").list();
	}

	@Override
	public List<ApplicationDocument> listDocumentFromBuilder() throws Exception {
		return entityOf(Builder.class).code("111456789").description("For Application1").list();
	}

}
