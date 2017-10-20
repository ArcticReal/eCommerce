package com.skytala.eCommerce.domain.product.relations.prodCatalog.event.invFacility;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.prodCatalog.model.invFacility.ProdCatalogInvFacility;
public class ProdCatalogInvFacilityDeleted implements Event{

	private boolean success;

	public ProdCatalogInvFacilityDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
