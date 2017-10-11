package com.skytala.eCommerce.domain.product.relations.productStoreFacility.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.productStoreFacility.model.ProductStoreFacility;
public class ProductStoreFacilityDeleted implements Event{

	private boolean success;

	public ProductStoreFacilityDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
