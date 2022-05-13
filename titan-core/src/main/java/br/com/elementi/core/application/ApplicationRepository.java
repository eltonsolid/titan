package br.com.elementi.core.application;

import java.util.List;

public interface ApplicationRepository {

	Application find(ApplicationIdentity create) throws Exception;

	List<Application> listApplication() throws Exception;

	List<Application> listApplication(String name) throws Exception;

	void insert(Application application);

	void update(Application application);

	List<ApplicationDocument> listDocument() throws Exception;

	List<ApplicationDocument> listDocumentCombinateOr() throws Exception;

	List<ApplicationDocument> listDocumentFromBuilder() throws Exception;

}
