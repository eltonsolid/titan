package br.com.elementi.core.application;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.elementi.core.domain.DomainEntity;
import br.com.elementi.core.model.Identity;
import br.com.elementi.core.tools.BuilderEntity.BuilderTemplate;

@Entity
@Table(name = "Document")
public class ApplicationDocument extends DomainEntity {

	/**
	 *
	 */
	private static final long serialVersionUID = 7904900923923617939L;

	@Id
	@Column(name = "document_id")
	private Identity id;

	@Column(name = "application_id")
	private ApplicationIdentity applicationIdentity;

	@Column(name = "code")
	private String code;

	@Column(name = "description")
	private String description;

	public interface Builder extends BuilderTemplate<ApplicationDocument> {

		public Builder code(String code);

		public Builder application(ApplicationIdentity identity);

		public Builder description(String code);
	}

	@Override
	public Identity getId() {
		return id;
	}

}
