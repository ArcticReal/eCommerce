package com.skytala.eCommerce.domain.product.relations.facility.event.productStore;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.facility.model.productStore.ProductStoreFacility;
public class ProductStoreFacilityDeleted implements Event{

	private boolean success;

	public ProductStoreFacilityDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
