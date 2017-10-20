package com.skytala.eCommerce.domain.product.relations.facility.event.productStore;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.facility.model.productStore.ProductStoreFacility;
public class ProductStoreFacilityAdded implements Event{

	private ProductStoreFacility addedProductStoreFacility;
	private boolean success;

	public ProductStoreFacilityAdded(ProductStoreFacility addedProductStoreFacility, boolean success){
		this.addedProductStoreFacility = addedProductStoreFacility;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public ProductStoreFacility getAddedProductStoreFacility() {
		return addedProductStoreFacility;
	}

}
