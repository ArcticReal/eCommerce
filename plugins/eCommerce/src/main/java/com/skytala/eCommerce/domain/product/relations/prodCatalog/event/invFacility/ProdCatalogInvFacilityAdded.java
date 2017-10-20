package com.skytala.eCommerce.domain.product.relations.prodCatalog.event.invFacility;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.prodCatalog.model.invFacility.ProdCatalogInvFacility;
public class ProdCatalogInvFacilityAdded implements Event{

	private ProdCatalogInvFacility addedProdCatalogInvFacility;
	private boolean success;

	public ProdCatalogInvFacilityAdded(ProdCatalogInvFacility addedProdCatalogInvFacility, boolean success){
		this.addedProdCatalogInvFacility = addedProdCatalogInvFacility;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public ProdCatalogInvFacility getAddedProdCatalogInvFacility() {
		return addedProdCatalogInvFacility;
	}

}
