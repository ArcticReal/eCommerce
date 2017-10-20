package com.skytala.eCommerce.domain.product.relations.facility.event.productLocation;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.facility.model.productLocation.ProductFacilityLocation;
public class ProductFacilityLocationUpdated implements Event{

	private boolean success;

	public ProductFacilityLocationUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
