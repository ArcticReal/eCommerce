package com.skytala.eCommerce.domain.product.relations.prodCatalog.event.role;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.prodCatalog.model.role.ProdCatalogRole;
public class ProdCatalogRoleDeleted implements Event{

	private boolean success;

	public ProdCatalogRoleDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
