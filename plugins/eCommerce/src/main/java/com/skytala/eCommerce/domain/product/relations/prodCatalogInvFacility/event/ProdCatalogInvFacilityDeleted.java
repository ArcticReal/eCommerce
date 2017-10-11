package com.skytala.eCommerce.domain.product.relations.prodCatalogInvFacility.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.prodCatalogInvFacility.model.ProdCatalogInvFacility;
public class ProdCatalogInvFacilityDeleted implements Event{

	private boolean success;

	public ProdCatalogInvFacilityDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
