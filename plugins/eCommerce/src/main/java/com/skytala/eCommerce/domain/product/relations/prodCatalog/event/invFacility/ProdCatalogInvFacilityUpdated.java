package com.skytala.eCommerce.domain.product.relations.prodCatalog.event.invFacility;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.prodCatalog.model.invFacility.ProdCatalogInvFacility;
public class ProdCatalogInvFacilityUpdated implements Event{

	private boolean success;

	public ProdCatalogInvFacilityUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
