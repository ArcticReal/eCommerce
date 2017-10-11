package com.skytala.eCommerce.domain.product.relations.prodCatalogRole.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.prodCatalogRole.model.ProdCatalogRole;
public class ProdCatalogRoleDeleted implements Event{

	private boolean success;

	public ProdCatalogRoleDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
