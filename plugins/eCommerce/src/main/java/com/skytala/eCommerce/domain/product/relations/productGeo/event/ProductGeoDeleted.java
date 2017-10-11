package com.skytala.eCommerce.domain.product.relations.productGeo.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.productGeo.model.ProductGeo;
public class ProductGeoDeleted implements Event{

	private boolean success;

	public ProductGeoDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
