package br.com.elementi.core.application;

import java.util.List;

public interface ApplicationService {

	public Application find(Integer id) throws Exception;

	List<Application> listApplication() throws Exception;

	void insert(Application application) throws Exception;

	void update(Application application) throws Exception;

	public List<ApplicationDocument> listDocument() throws Exception;

	public List<ApplicationDocument> listDocumentCombinateOr() throws Exception;

	public List<ApplicationDocument> listDocumentFromBuilder() throws Exception;

	public List<Application> listApplication(String string) throws Exception;

}