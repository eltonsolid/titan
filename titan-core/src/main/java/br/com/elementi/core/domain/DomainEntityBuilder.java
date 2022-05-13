
package br.com.elementi.core.domain;

import br.com.elementi.core.model.Identity;
import br.com.elementi.core.tools.BuilderEntity;
import br.com.elementi.core.tools.BuilderEntity.BuilderTemplate;
import br.com.elementi.core.tools.Reflect;

public abstract class DomainEntityBuilder<T extends BuilderTemplate<? extends DomainEntity>> extends DomainEntity {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7852559706351266342L;

	protected DomainEntityBuilder() {
	}
	
	public T builder(Identity id) throws Exception {
		return BuilderEntity.entity(Reflect.getClassDeclarated(getClass())).id(id);
	}

}
