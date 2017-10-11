package com.skytala.eCommerce.domain.product.relations.productFacility.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.productFacility.model.ProductFacility;
public class ProductFacilityUpdated implements Event{

	private boolean success;

	public ProductFacilityUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
