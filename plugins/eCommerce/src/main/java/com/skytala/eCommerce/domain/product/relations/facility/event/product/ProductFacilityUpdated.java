package com.skytala.eCommerce.domain.product.relations.facility.event.product;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.facility.model.product.ProductFacility;
public class ProductFacilityUpdated implements Event{

	private boolean success;

	public ProductFacilityUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
