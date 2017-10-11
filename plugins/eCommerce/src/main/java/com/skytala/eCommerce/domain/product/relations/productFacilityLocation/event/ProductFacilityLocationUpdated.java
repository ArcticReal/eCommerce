package com.skytala.eCommerce.domain.product.relations.productFacilityLocation.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.productFacilityLocation.model.ProductFacilityLocation;
public class ProductFacilityLocationUpdated implements Event{

	private boolean success;

	public ProductFacilityLocationUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
