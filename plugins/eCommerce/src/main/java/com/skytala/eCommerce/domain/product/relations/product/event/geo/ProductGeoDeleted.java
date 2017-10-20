package com.skytala.eCommerce.domain.product.relations.product.event.geo;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.product.model.geo.ProductGeo;
public class ProductGeoDeleted implements Event{

	private boolean success;

	public ProductGeoDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
