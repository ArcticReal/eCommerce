package com.skytala.eCommerce.domain.product.relations.productGeo.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.productGeo.model.ProductGeo;
public class ProductGeoUpdated implements Event{

	private boolean success;

	public ProductGeoUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
