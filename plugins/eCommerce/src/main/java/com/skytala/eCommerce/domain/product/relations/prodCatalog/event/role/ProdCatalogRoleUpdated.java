package com.skytala.eCommerce.domain.product.relations.prodCatalog.event.role;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.prodCatalog.model.role.ProdCatalogRole;
public class ProdCatalogRoleUpdated implements Event{

	private boolean success;

	public ProdCatalogRoleUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
