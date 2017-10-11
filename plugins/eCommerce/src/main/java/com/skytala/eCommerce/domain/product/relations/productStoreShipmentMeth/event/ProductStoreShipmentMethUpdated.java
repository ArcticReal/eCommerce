package com.skytala.eCommerce.domain.product.relations.productStoreShipmentMeth.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.productStoreShipmentMeth.model.ProductStoreShipmentMeth;
public class ProductStoreShipmentMethUpdated implements Event{

	private boolean success;

	public ProductStoreShipmentMethUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
