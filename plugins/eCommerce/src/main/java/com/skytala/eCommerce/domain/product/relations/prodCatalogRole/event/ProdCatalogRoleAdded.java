package com.skytala.eCommerce.domain.product.relations.prodCatalogRole.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.prodCatalogRole.model.ProdCatalogRole;
public class ProdCatalogRoleAdded implements Event{

	private ProdCatalogRole addedProdCatalogRole;
	private boolean success;

	public ProdCatalogRoleAdded(ProdCatalogRole addedProdCatalogRole, boolean success){
		this.addedProdCatalogRole = addedProdCatalogRole;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public ProdCatalogRole getAddedProdCatalogRole() {
		return addedProdCatalogRole;
	}

}
