package com.skytala.eCommerce.domain.product.relations.productFacility.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.productFacility.model.ProductFacility;
public class ProductFacilityDeleted implements Event{

	private boolean success;

	public ProductFacilityDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
