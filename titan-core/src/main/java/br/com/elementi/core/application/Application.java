package br.com.elementi.core.application;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.elementi.core.domain.DomainEntityBuilder;
import br.com.elementi.core.model.Identity;
import br.com.elementi.core.tools.BuilderEntity.BuilderTemplate;

@Entity
@Table(name = "APPLICATION")
public class Application extends DomainEntityBuilder<Application.Builder> {

	/**
	 *
	 */
	private static final long serialVersionUID = -8855427809154554140L;

	@Id
	@Column(name = "application_id")
	private Identity id;

	@Column(name = "application_id", insertable = false, updatable = false)
	private ApplicationIdentity applicationIdentity;

	@Column
	private String name;

	@Column
	private String description;

	@Column(name = "created")
	@Temporal(TemporalType.TIMESTAMP)
	private Date created;

	private Application() {
		super();
	}

	public static Builder create(Identity id) throws Exception {
		return new Application().builder(id);
	}

	public interface Builder extends BuilderTemplate<Application> {

		public Builder applicationIdentity(ApplicationIdentity identity);

		public Builder name(String value);

		public Builder descrption(String value);

	}

	@Override
	public Identity getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public Date getCreated() {
		return created;
	}

	public void setId(Integer id) throws Exception {
		this.id = Identity.create(id);
	}

	public void setName(String name) {
		this.name = name;
	}

}
