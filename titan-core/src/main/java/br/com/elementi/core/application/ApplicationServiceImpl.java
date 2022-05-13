package br.com.elementi.core.application;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ApplicationServiceImpl implements ApplicationService {

	@Autowired
	private ApplicationRepository repository;

	@Override
	public Application find(Integer id) throws Exception {
		return repository.find(ApplicationIdentity.create(id));
	}
	
	@Override
	public List<Application> listApplication(String name) throws Exception {
		return repository.listApplication(name);
	}

	public List<Application> listApplication() throws Exception {
		return repository.listApplication();
	}

	public void insert(Application application) throws Exception {
		repository.insert(application);
	}

	public void update(Application application) throws Exception {
		repository.update(application);
	}

	@Override
	public List<ApplicationDocument> listDocument() throws Exception {
		return repository.listDocument();
	}

	@Override
	public List<ApplicationDocument> listDocumentCombinateOr() throws Exception {
		return repository.listDocumentCombinateOr();
	}

	@Override
	public List<ApplicationDocument> listDocumentFromBuilder() throws Exception {
		return repository.listDocumentFromBuilder();
	}
}
